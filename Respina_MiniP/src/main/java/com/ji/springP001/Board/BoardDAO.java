package com.ji.springP001.Board;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ji.springP001.Comment.Comment;
import com.ji.springP001.Comment.commentMapper;
import com.ji.springP001.Member.Member;

@Service
public class BoardDAO {

	private int boardCount;
	private int MyBoardCount;
	private int MySearchBoardCount;
	private String input=null;
	
	@Autowired
	private SqlSession ss;
	
	public void getBoardCount(HttpServletRequest req) {
		
		boardCount = ss.getMapper(boardMapper.class).getBoardCount();
		
	}
	
	public void getMyBoardCount(HttpServletRequest req) {
		
		Member m = (Member) req.getSession().getAttribute("loginCheck");
		MyBoardCount = ss.getMapper(boardMapper.class).getMyBoardCount(m.getM_id());
		req.setAttribute("brd", MyBoardCount);
		
	}
	
	public void getMySearchedBoardCount(HttpServletRequest req) {
		
		// 사이드 탭에서 넘긴 파라미터는 보드에 유지되지 않으므로,
		// 미리 변수를 하나 생성해서 검색어 저장 및 새 검색어 입력시 교체 작업
		if(input==null) {
			input = req.getParameter("search_board");
		}else if(req.getParameter("search_board")!=null && !input.equals(req.getParameter("search_board"))) {
			input = req.getParameter("search_board");
		}
		MySearchBoardCount = ss.getMapper(boardMapper.class).getSearchedBoardCount(input,input);
		
	}
	
	public void getCommentdB(HttpServletRequest req) {
		
		Member m = (Member) req.getSession().getAttribute("loginCheck");
		List<Board> bl = ss.getMapper(boardMapper.class).getCommentedBoard(m.getM_id());
		req.setAttribute("boards", bl);
		
	}

	public void getAllBoard(int pageNo,HttpServletRequest req) {

		getBoardCount(req);
		int boardPerPage = 2;
		int pageCount = (int) Math.ceil(boardCount / (double) boardPerPage); // 소수점 올림
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageNo", pageNo);

		int param1 = boardPerPage * (pageNo - 1) + 1;
		int param2 = (pageNo == pageCount) ? boardCount : (param1 + boardPerPage - 1);
		
		List<Board> bl = ss.getMapper(boardMapper.class).getAllBoard(param1,param2);
		req.setAttribute("boards", bl);

	}

	public void getMyBoard(int pageNo,HttpServletRequest req) {
	
		getMyBoardCount(req);
		int boardPerPage = 2;
		int pageCount = (int) Math.ceil(MyBoardCount / (double) boardPerPage); // 소수점 올림
		
		// 같은 pageCount로 주게 되면, 페이지 갯수가 공유가 되버림.(빈 페이지 나옴)
		req.setAttribute("pageCount2", pageCount);
		req.setAttribute("pageNo2", pageNo);
		
		int param1 = boardPerPage * (pageNo - 1) + 1;
		int param2 = (pageNo == pageCount) ? MyBoardCount : (param1 + boardPerPage - 1);
		
		// Mapper의 Parameter는 한 종류밖에 사용할 수 없기에, String으로 통일하여 사용하기 위함.
		// 전체 글 찾기는 (param1,param2)를 인자로 1~3 / 4~6 / ... 처럼 boardPerPage로 나누었음.
		String param11 = Integer.toString(param1);
		String param22 = Integer.toString(param2);
		
		Member m = (Member) req.getSession().getAttribute("loginCheck");
		List<Board> bl = ss.getMapper(boardMapper.class).getMyBoard(param11,param22,m.getM_id());
		req.setAttribute("boards", bl);

	}
	
	public void getMySearchedBoard(HttpServletRequest req,int pageNo,String b) {
		
		getMySearchedBoardCount(req);
		int boardPerPage = 2;
		int pageCount = (int) Math.ceil(MySearchBoardCount / (double) boardPerPage); // 소수점 올림
		
		req.setAttribute("pageCount3", pageCount);
		req.setAttribute("pageNo3", pageNo);
			
		int param1 = boardPerPage * (pageNo - 1) + 1;
		int param2 = (pageNo == pageCount) ? MySearchBoardCount : (param1 + boardPerPage - 1);	

		String param11 = Integer.toString(param1);
		String param22 = Integer.toString(param2);

		
		List<Board> bl = ss.getMapper(boardMapper.class).getSearchedBoard(param11,param22,
				b,b);
		req.setAttribute("boards", bl);
		
	}

	public void insertBoard(HttpServletRequest req) {

		String t1 = req.getParameter("token");
		String t2 = (String) req.getSession().getAttribute("st");
		Member m = (Member) req.getSession().getAttribute("loginCheck");
		Board b = new Board();

		if (t2 != null && t1.equals(t2)) {
			req.setAttribute("result", "오류/글 등록 후 새로고침");
		} else {

			try {

				b.setB_owner(m.getM_id());
				b.setB_text(req.getParameter("write_board"));
				Date da = new Date();
				b.setB_when(da);

				try {
					boardMapper bm = ss.getMapper(boardMapper.class);
					bm.insertBoard(b);
					req.setAttribute("result", "게시글 등록 성공!");
					req.getSession().setAttribute("st", t1);
				} catch (Exception e) {
					e.printStackTrace();
					req.setAttribute("result", "오류/게시글 등록 실패");
				}

			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("result", "오류/보드 생성 실패");
			}

		}
		
	}
	
	public void deleteBoard(HttpServletRequest req,int b_no) {

		try {
			
			boardMapper bm = ss.getMapper(boardMapper.class);
			bm.deleteMyBoard(b_no);
			req.setAttribute("result", "삭제 성공");
			
		} catch (Exception e) {
			req.setAttribute("result", "삭제 실패");
		}
		
	}
	
	public void updateBoard(HttpServletRequest req,int b_no,String b_text) {
		
		try {
			
			boardMapper bm = ss.getMapper(boardMapper.class);
			String no = Integer.toString(b_no);
			bm.updateBoard(b_text, no);
			req.setAttribute("result", "수정 성공");
			
		} catch (Exception e) {
			req.setAttribute("result", "수정 실패");
		}
		
	}

}
