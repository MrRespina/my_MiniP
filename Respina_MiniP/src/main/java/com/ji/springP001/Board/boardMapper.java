package com.ji.springP001.Board;

import java.util.List;

public interface boardMapper {

	public abstract int getBoardCount();
	public abstract List<Board> getAllBoard(int param1,int param2);
	public abstract int getMyBoardCount(String param1);
	public abstract List<Board> getMyBoard(String param1,String param2,String param3);
	public abstract int getSearchedBoardCount(String param1,String param2);
	public abstract List<Board> getSearchedBoard(String param1,String param2,String param3,String param4);
	public abstract List<Board> getCommentedBoard(String c_owner);
	public abstract void insertBoard(Board b);
	public abstract void deleteMyBoard(int b_no);
	public abstract void updateBoard(String b_no,String b_text);
	
}
