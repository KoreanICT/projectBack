package kr.co.ictedu.projectBack.controller;

import kr.co.ictedu.projectBack.vo.StockVO;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.co.ictedu.projectBack.vo.CommunityVO;
import kr.co.ictedu.projectBack.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.projectBack.common.PagingService;
import kr.co.ictedu.projectBack.service.StockService;
import kr.co.ictedu.projectBack.vo.StockImagesVO;
import kr.co.ictedu.projectBack.vo.StockVO;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final PageVO pageVO;

	@Autowired
    private StockService stockService;
    
    @Autowired
    private PagingService pagingService;// 주석 페이징 처리다.

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

	StockController(PageVO pageVO) {
		this.pageVO = pageVO;
	}

    @PostMapping("/insert")
    public ResponseEntity<?> insertStock(
            @ModelAttribute StockVO svo,
            @RequestParam(value = "mfile", required = false) MultipartFile mfile) {

        List<StockImagesVO> imageList = new ArrayList<>();

        if (mfile != null && !mfile.isEmpty()) {
            try {
                File folder = new File(filePath, "stock");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String originalName = mfile.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                String saveName = UUID.randomUUID().toString() + extension;
                File saveFile = new File(folder, saveName);

                mfile.transferTo(saveFile);

                StockImagesVO imageVO = new StockImagesVO();
                imageVO.setStockimage(saveName);
                imageList.add(imageVO);

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패");
            }
        }

        svo.setGetimglist(imageList);
        stockService.insertStock(svo, imageList);

        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("message", "도서 등록 성공");
        response.put("snum", svo.getSnum());

        return ResponseEntity.ok(response);
    }
    
    @RequestMapping("/communityList")
	public Map<String, Object> upCommunityList(
			@RequestParam Map<String, String> paramMap, 
			HttpServletRequest request
			) {
		// 현재 페이지에 따라 페이지 공식에 의해서 begin , end를 구해서
		// 페이징 처리되어서 반환 받은 데이터
		
		String cPage = paramMap.get("cPage");
		System.out.println("searchType : " + paramMap.get("searchType"));
		System.out.println("searchValue : " + paramMap.get("searchValue"));
		System.out.println("*************************");
		int totalCnt = stockService.totalCount(paramMap);
		PageVO pageVO = pagingService.makePage(totalCnt, cPage);
		
		// Json으로 응답 처리 - 페이징 처리된 결과 리스트와 정보
		Map<String, String> map = new HashMap<>(paramMap);
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		List<StockVO> list = stockService.stockList(map);
		
        Map<String, Object> response = new HashMap<>();
		response.put("data", list); // 페이징 처리가 완료된 리스트를 저장한 데이터
		response.put("totalItems", pageVO.getTotalRecord()); // 전체 게시물의 count
		response.put("totalPages", pageVO.getTotalPage()); // 전체 페이지
		response.put("currentPage", pageVO.getNowPage()); // 현재 페이지
		response.put("startPage", pageVO.getStartPage()); // 블록의 시작
		response.put("endPage", pageVO.getEndPage()); // 블록의 끝
		
		return response;
	}
    
    @GetMapping("/list")
    public List<StockVO> stockList(@RequestParam Map<String, String> map) {
        return stockService.stockList(map);
    }
    
    @GetMapping("/{snum}")
    public StockVO stockDetail(@PathVariable("snum") int snum) {
        return stockService.stockDetail(snum);
    }
    
    
    
    @PutMapping("/{snum}")
    public ResponseEntity<?> updateStock(
            @PathVariable("snum") int snum,
            @ModelAttribute StockVO svo,
            @RequestParam(value = "mfile", required = false) MultipartFile mfile) {

        svo.setSnum(snum);
        List<StockImagesVO> imageList = new ArrayList<>();

        if (mfile != null && !mfile.isEmpty()) {
            try {
                File folder = new File(filePath, "stock");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String originalName = mfile.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                String saveName = UUID.randomUUID().toString() + extension;
                File saveFile = new File(folder, saveName);

                mfile.transferTo(saveFile);

                StockImagesVO imageVO = new StockImagesVO();
                imageVO.setStockimage(saveName);
                imageVO.setStocksnum(snum);
                imageList.add(imageVO);

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("이미지 수정 실패");
            }
        }

        svo.setGetimglist(imageList);
        stockService.updateStock(svo, imageList);

        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("message", "도서 정보 수정 성공");
        response.put("snum", snum);

        return ResponseEntity.ok(response);
    }
    public ResponseEntity<?> deleteStock(@PathVariable("snum") int snum) {
        stockService.deleteStock(snum);

        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("message", "도서 삭제 성공");

        return ResponseEntity.ok(response);
    }
}