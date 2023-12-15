package com.ji.springP001.Comment;

import java.util.List;

public interface commentMapper {

	public abstract List<Comment> searchComments();
	public abstract List<Comment> searchMyComment(String m_id);
	public abstract int searchMyCommentCount(String owner);
	public abstract void insertComment(String b_no,String m_id,String c_text);
	public abstract void updateComments(String p1,String p2);
	public abstract void deleteComment(int c_no);
	
	
}
