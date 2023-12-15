package com.ji.springP001.Comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ji.springP001.Member.Member;


@Service
public class CommentDAO {

	@Autowired
	SqlSession ss;
	
	public void getComments(HttpServletRequest req) {
		
		List<Comment> cl = ss.getMapper(commentMapper.class).searchComments();
		req.setAttribute("comments", cl);
		
	}
	
	public void getMyComments(HttpServletRequest req) {
		
		Member m  = (Member) req.getSession().getAttribute("loginCheck");
		String owner = m.getM_id();
		List<Comment> cl = ss.getMapper(commentMapper.class).searchMyComment(owner);
		req.setAttribute("comments", cl);
		
	}
	
	public void getMyCommentCount(HttpServletRequest req) {
		
		Member m  = (Member) req.getSession().getAttribute("loginCheck");
		String owner = m.getM_id();
		commentMapper cm = ss.getMapper(commentMapper.class);
		int cnt = cm.searchMyCommentCount(owner);
		req.setAttribute("com", cnt);
		
	}
	
	public void insertComments(HttpServletRequest req) {
			
		String c_text = req.getParameter("cmtText");
		String b_no = req.getParameter("b_no");
		
		String t1 = req.getParameter("token");
		String t2 = (String) req.getSession().getAttribute("st");

			if (t2 != null && t1.equals(t2)) {
				
				req.setAttribute("result", "오류/댓글 등록 후 새로고침");
				
			} else {
			
				Member m  = (Member) req.getSession().getAttribute("loginCheck");
				String owner = m.getM_id();
				
				commentMapper cm = ss.getMapper(commentMapper.class);
				cm.insertComment(b_no, owner, c_text);
				
				req.setAttribute("comments", cm);
				req.getSession().setAttribute("st", t1);
				
		}
		
		
		
	}
	
	public void updateComments(HttpServletRequest req) {
		
		String text= req.getParameter("c_text");
		String no = req.getParameter("c_no");
		
		commentMapper cm = ss.getMapper(commentMapper.class);
		cm.updateComments(text, no);;
		req.setAttribute("comments", cm);
		
	}
	
	public void deleteComments(HttpServletRequest req) {
		
		String no = req.getParameter("c_no");
		int c_no = Integer.parseInt(no);
		
		commentMapper cm = ss.getMapper(commentMapper.class);
		cm.deleteComment(c_no);
		req.setAttribute("comments", cm);
		
	}
	
	
}
