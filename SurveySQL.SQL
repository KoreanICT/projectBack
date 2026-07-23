create sequence survey_seq increment by 1 start with 1;

CREATE TABLE survey (
  svnum number CONSTRAINT survey_num_pk PRIMARY key,
  code  number(10) NOT NULL,
  sub   varchar2(255) NOT NULL,
  sdate date NOT NULL,
  CONSTRAINT sur_code_check CHECK (code between 2 and 5));

CREATE TABLE survey_questions (
  svnum          number NOT NULL,
  questions_id   number(5),
  questions_text varchar2(50));
ALTER TABLE survey_questions ADD CONSTRAINT survey_questions_survey_FK FOREIGN KEY (svnum) REFERENCES survey (svnum) ON DELETE CASCADE;

CREATE TABLE survey_result (
  mnum         number NOT NULL,
  svnum        number NOT NULL,
  questions_id number(5) NOT NULL,
  rating       number(5) NOT NULL,
  request      clob,
  resdate      date NOT NULL);
ALTER TABLE survey_result ADD CONSTRAINT survey_content_member_FK FOREIGN KEY (mnum) REFERENCES member (mnum) ON DELETE CASCADE;
ALTER TABLE survey_result ADD CONSTRAINT survey_content_survey_FK FOREIGN KEY (svnum) REFERENCES survey (svnum) ON DELETE CASCADE;





