package com.ji.springP001.Comment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ji.springP001.TokenS;
import com.ji.springP001.Board.BoardController;
import com.ji.springP001.Board.BoardDAO;
import com.ji.springP001.Member.MemberDAO;

@Controller
public class CommentController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private CommentDAO cDAO;
	
	@Autowired
	private BoardController bCtr;
	
	@Autowired
	private BoardDAO bDAO;
	
	@RequestMapping(value = "/cmt.myComment", method = RequestMethod.GET)
	public String searchMyComment(HttpServletRequest req) throws Exception {

		if(mDAO.loginCheck(req)) {
			
			cDAO.getMyComments(req);
			bCtr.getComments(req);
			TokenS.makeTokenS(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("result", "내 댓글 보기");
			req.setAttribute("content", "comment/myComment.jsp");
			
		}else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			
		}
		
		return "index";
		

	}

	@RequestMapping(value = "/cmt.commentUpdateToBoard", method = RequestMethod.GET)
	public String commentUpdateToBoard(HttpServletRequest req) throws Exception {

		if(mDAO.loginCheck(req)) {
			
			cDAO.getComments(req);
			cDAO.updateComments(req);

			cDAO.getComments(req);
			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);	
			TokenS.makeTokenS(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("result", "댓글 수정 완료");
			req.setAttribute("content", "board/boardMain.jsp");
				
			return "index";
			
		} else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			return "index";
			
		}

	}
	
	@RequestMapping(value = "/cmt.commentUpdateToMy", method = RequestMethod.GET)
	public String commentUpdateToMy(HttpServletRequest req) throws Exception {
		
		if(mDAO.loginCheck(req)) {
			
			cDAO.getComments(req);
			cDAO.updateComments(req);
			
			cDAO.getMyComments(req);
			bCtr.getComments(req);
			TokenS.makeTokenS(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("result", "댓글 수정 완료");
			req.setAttribute("content", "comment/myComment.jsp");
			
			return "index";
			
		} else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			return "index";
			
		}
		
	}
	
	@RequestMapping(value = "/cmt.insertComment", method = RequestMethod.GET)
	public String insertComment(HttpServletRequest req) throws Exception {
		
		if(mDAO.loginCheck(req)) {
			
			cDAO.getComments(req);
			cDAO.insertComments(req);
				
			return bCtr.home(req);
			
		} else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			return "index";
			
		}
	}
	
	@RequestMapping(value = "/cmt.commentDeleteToBoard", method = RequestMethod.GET)
	public String deleteCommentToBoard(HttpServletRequest req) throws Exception {
		
	if(mDAO.loginCheck(req)) {
			
			cDAO.getComments(req);
			cDAO.deleteComments(req);

			cDAO.getComments(req);
			req.setAttribute("boardVal", "all");
			bDAO.getAllBoard(1, req);	
			TokenS.makeTokenS(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("result", "댓글 삭제 완료");
			req.setAttribute("content", "board/boardMain.jsp");
				
			return "index";
			
		} else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			return "index";
			
		}
		
	}
	
	@RequestMapping(value = "/cmt.commentDeleteToMy", method = RequestMethod.GET)
	public String deleteCommentToMy(HttpServletRequest req) throws Exception {
		
		if(mDAO.loginCheck(req)) {
			
			cDAO.getComments(req);
			cDAO.deleteComments(req);
			
			cDAO.getMyComments(req);
			bCtr.getComments(req);
			TokenS.makeTokenS(req);
			mDAO.loginCheck(req);
			
			req.setAttribute("result", "댓글 삭제 완료");
			req.setAttribute("content", "comment/myComment.jsp");
				
			return "index";
			
		} else {
			
			req.setAttribute("result", "로그인 만료됨!");
			req.setAttribute("content", "mainPage/home.jsp");
			return "index";
			
		}
		
	}

}
