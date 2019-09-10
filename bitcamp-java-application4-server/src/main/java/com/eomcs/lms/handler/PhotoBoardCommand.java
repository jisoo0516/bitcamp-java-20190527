package com.eomcs.lms.handler;

import java.io.IOException;
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
    out.println("<html><head><title>사진 등록폼</title></head>");
    out.println("<body><h1>사진 등록폼</h1>");
    out.println("<form action='/photoboard/add'>");
    out.println(" 제목 : <input type='text' name = 'title'> <br>");
    out.println(" 수업번호 : <input type='text' name = 'lessonNo'> <br>");
    out.println(" 사진1 : <input type='text' name = 'filePath1'> <br>");
    out.println(" 사진2 : <input type='text' name = 'filePath2'> <br>");
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

      int count = 0;
      for (int i = 1; i <= 6; i++) {
        String filepath = request.getParameter("filePath");
        if (filepath.length() == 0) {
           continue;
        }
        
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
        
      }
        if (count == 0) {
          throw new Exception("사진 파일 없음!");
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
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
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
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");

      }else {
        out.println("<form action='/photoboard/update'>");
        out.printf("번호 : <input type='text' name='no' value='%d' readonly><br>\n",
            photoBoard.getNo());
        out.printf("내용:<input type='text' name='title' value='%s'><br>\n", 
            photoBoard.getTitle());
        out.printf("작성일:<input type='text' name='reportingDate' value='%s'><br>\n", 
            photoBoard.getReportingDate());
        out.printf("조회수: <input type='text' name='hits' value='%d'><br>\n", 
            photoBoard.getHits());
        out.printf("번호: <input type='text' name='lessonNo' value='%d'><br>\n", 
            photoBoard.getLessonNo());
        out.println("사진 파일:");
        photoBoardDao.increaseViewCount(no);

        List<PhotoFile> files = photoBoard.getFiles();
        for (int i = 1; i <= 6; i++) {
          if (i <= files.size()) {
            out.printf("사진%d: <input type='text' name='filePath%d' value='%s'><br>\n",
                i, i, files.get(i-1).getFilePath());
          } else {
            out.printf("사진%d: <input type='text' name='filePath%d'><br>\n",
                i, i);
          }
        }
        out.println("<button>변경</button>");
        out.printf("<a href='/photoboard/delete?no=%d'>삭제</a>\n", 
            photoBoard.getNo());
        out.println("</form>");
      }
      
    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
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
  public void update(ServletRequest request, ServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진 변경</h1>");
    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setNo(Integer.parseInt(request.getParameter("no")));
      photoBoard.setTitle(request.getParameter("title"));

      photoBoardDao.update(photoBoard);
      photoFileDao.deleteAll(photoBoard.getNo());

      int count = 0;
      for (int i = 1; i <= 6; i++) {
        String filepath = request.getParameter("filePath" + i);
        if (filepath.length() == 0) {
          continue;
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      
      if (count == 0) {
        out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
        throw new Exception("사진 파일 없음!");
      }
      
      out.println("<p>변경하였습니다.</p>");
      
    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다!</p>");
      throw new RuntimeException(e);
    
    } finally {
      out.println("</body></html>");
    }
  }

}