package com.ji.springP001.Board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ji.springP001.TokenS;
import com.ji.springP001.Comment.CommentDAO;
import com.ji.springP001.Member.MemberDAO;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO bDAO;

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private CommentDAO cDAO;

	private String search = null;

	@RequestMapping(value = "/board.go", method = RequestMethod.GET)
	public String home(HttpServletRequest req) throws Exception {

		// 전체 게시글에서 페이지를 넘기는 것과, 내 게시글에서 페이지를 넘기는 것을 구분하기 위해 만듬.
		mDAO.loginCheck(req);
		cDAO.getComments(req);
		TokenS.makeTokenS(req);
		
		req.setAttribute("boardVal", "all");
		if (req.getParameter("p") == null) {

			bDAO.getAllBoard(1, req);
			req.setAttribute("result", "게시판 페이지");
			req.setAttribute("content", "board/boardMain.jsp");

		} else {

			bDAO.getAllBoard(Integer.parseInt(req.getParameter("p")), req);
			req.setAttribute("result", "게시판 페이지");
			req.setAttribute("content", "board/boardMain.jsp");

		}

		return "index";

	}

	@RequestMapping(value = "/board.getmyBoard", method = RequestMethod.GET)
	public String getMyBoard(HttpServletRequest req) throws Exception {

		cDAO.getComments(req);
		TokenS.makeTokenS(req);
		
		if (mDAO.loginCheck(req)) {

			if (req.getParameter("p") == null) {

				req.setAttribute("boardVal", "my");
				bDAO.getMyBoard(1, req);
				req.setAttribute("result", "내 글 보기");
				req.setAttribute("content", "board/boardMain.jsp");

			} else {

				req.setAttribute("boardVal", "my");
				bDAO.getMyBoard(Integer.parseInt(req.getParameter("p")), req);
				req.setAttribute("result", "내 글 보기");
				req.setAttribute("content", "board/boardMain.jsp");

			}

		} else {

			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "board/boardMain.jsp");

		}

		return "index";

	}

	@RequestMapping(value = "/board.getSearchedBoard", method = RequestMethod.GET)
	public String getMySearchedBoard(HttpServletRequest req) throws Exception {

		// 검색 탭에서 가져온 정보를 저장하기 위함.(검색 탭의 정보는 첫 사이드탭 검색 이후 받아 올 수 없다!)

		cDAO.getComments(req);
		TokenS.makeTokenS(req);
		
		if (mDAO.loginCheck(req)) {

			if (req.getParameter("p") == null) {

				search = req.getParameter("search_board");
				req.setAttribute("boardVal", "searched");
				bDAO.getMySearchedBoard(req, 1, search);
				req.setAttribute("result", "검색한 글 보기");
				req.setAttribute("content", "mainPage/home.jsp");
				req.setAttribute("content", "board/boardMain.jsp");

			} else {

				
				req.setAttribute("boardVal", "searched");
				bDAO.getMySearchedBoard(req, Integer.parseInt(req.getParameter("p")), search);
				req.setAttribute("result", "검색한 글 보기");
				req.setAttribute("content", "board/boardMain.jsp");

			}

		} else {

			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "board/boardMain.jsp");

		}

		return "index";

	}
	
	@RequestMapping(value = "/board.getMyComment", method = RequestMethod.GET)
	public void getComments(HttpServletRequest req) throws Exception {

		bDAO.getCommentdB(req);

	}

	@RequestMapping(value = "/board.writeBoard", method = RequestMethod.GET)
	public String writeBoard(HttpServletRequest req) throws Exception {

		
		if (mDAO.loginCheck(req)) {
			
			bDAO.insertBoard(req);
			cDAO.getComments(req);
			TokenS.makeTokenS(req);	
			mDAO.loginCheck(req);
			
			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);
			req.setAttribute("content", "board/boardMain.jsp");
		} else {
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
		}

		return "index";

	}

	@RequestMapping(value = "/board.boardInfo", method = RequestMethod.GET)
	public String boardInfo(HttpServletRequest req) throws Exception {

		if (mDAO.loginCheck(req)) {

			TokenS.makeTokenS(req);
			req.setAttribute("b_no", req.getParameter("b_no"));
			req.setAttribute("b_owner", req.getParameter("b_owner"));
			req.setAttribute("b_text", req.getParameter("b_text"));
			req.setAttribute("result", "보드 상세정보");
			req.setAttribute("content", "board/boardInfo.jsp");

		} else {

			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");

		}

		return "index";

	}

	@RequestMapping(value = "/board.deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(HttpServletRequest req) throws Exception {

		if (mDAO.loginCheck(req)) {

			bDAO.deleteBoard(req, Integer.parseInt(req.getParameter("b_no")));
			TokenS.makeTokenS(req);
			cDAO.getComments(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);
			req.setAttribute("content", "board/boardMain.jsp");

		} else {

			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");

		}

		return "index";

	}

	@RequestMapping(value = "/board.updateBoard", method = RequestMethod.GET)
	public String updateBoard(HttpServletRequest req) throws Exception {

		if (mDAO.loginCheck(req)) {

			bDAO.updateBoard(req, Integer.parseInt(req.getParameter("b_no")), req.getParameter("b_text"));
			TokenS.makeTokenS(req);
			cDAO.getComments(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);
			req.setAttribute("content", "board/boardMain.jsp");

		} else {

			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");

		}

		return "index";

	}

}
