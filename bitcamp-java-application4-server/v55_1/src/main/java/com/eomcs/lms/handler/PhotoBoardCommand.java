package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;
@Component
public class PhotoBoardCommand  {

  // 이 클래스에서 로그를 출력할 일이 있다면 다음과 같이 로거를 만들어 사용하라!
  /*
  private static final Logger logger = LogManager.getLogger(PhotoBoard.class);
   */
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;


  public PhotoBoardCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) throws IOException{
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @RequestMapping("/photoboard/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 등록폼</title></head>");
    out.println("<body><h1>게시물 등록폼</h1>");
    out.println("<form action='/photoboard/add'>");
    out.println(" 제목 : <input type='text' name = 'title'> <br>");
    out.println(" 수업번호 : <input type='text' name = 'lessonNo'> <br>");
    out.println(" 사진1 : <input type='text' name = 'filePath'> <br>");
    out.println(" 사진2 : <input type='text' name = 'filePath'> <br>");
    out.println("<button>등록</button>");
    out.println(" </form>>");
    out.println("<body><html>");
  }

  @Transactional
  @RequestMapping("/photoboard/add") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void add(ServletRequest request, ServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진 등록</h1>");

    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

      photoBoardDao.insert(photoBoard);

      out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
      out.println("<p>파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.</p>");
      out.flush();

      int count = 0;
      while (true) {
        String filepath = request.getParameter("filePath");
        if (filepath.length() == 0) {
          if (count > 0) {
            break;
          } else {
            out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
            continue;
          }
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;

      }
      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패</p>");
      throw new RuntimeException(e);
    } 
  }
  @Transactional
  @RequestMapping("/photoboard/delete") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void delete(ServletRequest request, ServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'>"
        + "</head>");
    out.println("<body><h1>사진삭제</h1>");
    try {

      int no = Integer.parseInt(request.getParameter("no"));


      if(photoBoardDao.findBy(no) == null) {
        out.println("<p>해당 데이터가 없습니다</p>");

      }
      // 먼저 게시물의 첨부파일을 삭제한다.
      photoFileDao.deleteAll(no);

      // 게시물을 삭제한다.
      photoBoardDao.delete(no);

      out.println("데이터 삭제하였습니다.");


    } catch (Exception e) {
      out.println("<p>데이터 삭제 실패</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }


  @RequestMapping("/photoboard/detail") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void detail(ServletRequest request, ServletResponse response)throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 상세</title></head>");
    out.println("<body><h1>사진 상세</h1>");

    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no =  Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");

      }else {

        photoBoardDao.increaseViewCount(no);
        out.printf("내용: %s\n", photoBoard.getTitle());
        out.printf("작성일: %s\n", photoBoard.getReportingDate());
        out.printf("조회수: %d\n", photoBoard.getHits());
        out.printf("번호: %d\n", photoBoard.getLessonNo());
        out.println("사진 파일:");

        List<PhotoFile> files = photoBoard.getFiles();
        for(PhotoFile file : files) {
          out.printf(">%s\n", file.getFilePath());
        }
      }

    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패!</p>");
      throw new RuntimeException(e);
    }
    out.println("<body><html>");
  }

  @RequestMapping("/photoboard/list") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void list(ServletRequest request, ServletResponse response)throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 목록</title>"
        + "<link rel=\'stylesheet\' href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "</head>");
    out.println("<body><h1>사진 목록</h1>");
    out.println("<a href='/photoboard/form'>새 글</a><br>");
    try {
      out.println("<table class='table table-hover'>");
      out.println("<tr><th>번호</th><th>제목</th><th>등록일</th><th>조회수</th><th>수업</th></tr>");
      List<PhotoBoard> photoboards = photoBoardDao.findAll();
      for (PhotoBoard photoboard : photoboards) {
        out.printf("<tr><td>%d</td>"
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td>"
            + "<td>%s</td><td>%d</td><td>%d</td></tr>\n",
            photoboard.getNo(), 
            photoboard.getNo(),
            photoboard.getTitle(),
            photoboard.getReportingDate(),
            photoboard.getHits(),
            photoboard.getLessonNo());
      }
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>데이터 목록조회 실패</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @Transactional
  @RequestMapping("/photoboard/update")// 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void update(BufferedReader in, PrintStream out)throws IOException {
    try {



      int no = Input.getIntValue(in, out, "번호?");

      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      if (photoBoard == null) {
        out.println("해당 번호를 찾을 수 없습니다.");
        return;
      }
      out.println("제목을 입력하지 않으면 이전 제목을 유지합니다.");

      PhotoBoard data = new PhotoBoard();
      data.setNo(no);

      String str = Input.getStringValue(in, out, String.format("제목(%s)?", photoBoard.getTitle()));

      // 제목을 입력했으면 사진 게시글의 제목을 변경한다.
      if (str.length() > 0) {
        data.setTitle(str);
      }
      photoBoardDao.update(data);
      out.println("게시물의 제목을 변경하였습니다");


      // 이전에 등록한 파일 목록을 출력한다.
      out.println("사진 파일:");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for(PhotoFile file : files) {
        out.printf(">%s\n", file.getFilePath());
      }

      // 파일을 변경할지 여부를 묻는다.
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      String response = Input.getStringValue(in, out, "사진을 변경하시겠습니까?(y/N)");

      if(!response.equalsIgnoreCase("y"))  {
        out.println("파일 변경을 취소합니다.");
        return;
      }

      // 기존 사진 파일을 삭제한다.
      photoFileDao.deleteAll(no);

      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      out.flush();

      int count = 0;
      while(true) {
        String filepath = Input.getStringValue(in, out, "사진 파일?");
        if(filepath.length() == 0) {
          if(count > 0) {
            break;
          }else {
            out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          }
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }


      out.println("사진을 변경하였습니다.");

    } catch (Exception e) {
      out.println("데이터 변경 실패");
      throw new RuntimeException(e);
    }

  }
}
