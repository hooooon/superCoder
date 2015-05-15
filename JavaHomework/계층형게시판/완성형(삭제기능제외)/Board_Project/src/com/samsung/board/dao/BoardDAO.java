package com.samsung.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.samsung.board.util.JDBCUtil;
import com.samsung.board.vo.BoardVO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	public void addReplyHelper(int currentSeq){
		conn = JDBCUtil.getConnection();
		sql = "UPDATE aboard SET group_seq=group_seq+1 " +
				"WHERE family = (select family from aboard where seq=?) and " +
				"group_seq > (select group_seq from aboard where seq=?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentSeq);
			pstmt.setInt(2, currentSeq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}	
	}
	
	public void addReplyBoard(BoardVO vo, int currentSeq){
		if(currentSeq!=0){
			addReplyHelper(currentSeq);
		}
		conn = JDBCUtil.getConnection();
		
		sql= "insert into aboard(seq, family, group_seq, depth, title, nickname, content, regdate, userid, parent) " +
			"values( (select nvl(max(seq), 0)+1 from aboard), ";
		
		try {
			if(currentSeq!=0){	
				sql+="(select family from aboard where seq=?), " +
						"(select group_seq+1 from aboard where seq=?), " +
						"(select depth+1 from aboard where seq=?), " +
						"?, ?, ?, sysdate, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, currentSeq);
				pstmt.setInt(2, currentSeq);
				pstmt.setInt(3, currentSeq);
				pstmt.setString(4, vo.getTitle());
				pstmt.setString(5, vo.getNickname());
				pstmt.setString(6, vo.getContent());
				pstmt.setString(7, vo.getUserid());
				pstmt.setInt(8, currentSeq);
			}else{
				sql+="(select nvl(max(seq), 0)+1 from aboard), ?, ?, ?, ?, ?, sysdate, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, 0);
				pstmt.setString(3, vo.getTitle());
				pstmt.setString(4, vo.getNickname());
				pstmt.setString(5, vo.getContent());
				pstmt.setString(6, vo.getUserid());
				pstmt.setInt(7, currentSeq);
			}
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}
		
	}
	
	public void deleteReplyHelper(BoardVO vo){
		conn = JDBCUtil.getConnection();
		sql= "UPDATE aboard SET group_seq=group_seq-1 " +
				"WHERE family = (select family from aboard where seq=?) " +
				"and group_seq > ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getGroup_seq());
			pstmt.setInt(2, vo.getGroup_seq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}
		
	}
	
	public void deleteReplyBoard(BoardVO vo){
		deleteReplyHelper(vo);
		conn = JDBCUtil.getConnection();
		sql= "delete aboard where seq=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}
		deleteChild(vo.getSeq());
	}
	
	public void deleteChild(int parentSeq){
		if(checkChild(parentSeq)[0]==1){
			conn = JDBCUtil.getConnection();
			sql= "delete aboard where seq=?";

			try {
				System.out.println(checkChild(parentSeq)[1]+"를 지움");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, checkChild(parentSeq)[1]);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				JDBCUtil.close(conn, pstmt);
			}
			BoardVO vo = new BoardVO();
			vo.setSeq(checkChild(parentSeq)[1]);
			deleteReplyHelper(vo);
			deleteChild(checkChild(parentSeq)[1]);
		}
	}
	
	public int[] checkChild(int parentSeq){
		conn = JDBCUtil.getConnection();
		sql= "select parent, seq from aboard where parent=?";
		int[] isChild = {0, 0};
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parentSeq);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println(rs.getInt("parent")+"의 자식"+rs.getInt("seq")+"가 존재");
				isChild[0] = 1;
				isChild[1] = rs.getInt("seq");
			}else{
				isChild[0] = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt, rs);
		}
		return isChild;
	}
	
	public void updateBoard(BoardVO vo){
		conn = JDBCUtil.getConnection()	;
		sql = "UPDATE aboard set title=?, content=? where seq=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}		
	}
	
	public void updateCount(BoardVO vo){
		conn = JDBCUtil.getConnection();
		sql= "UPDATE aboard set cnt=cnt+1 where seq=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);
		}		
	}
	
	public BoardVO getBoard(BoardVO vo){
		updateCount(vo);
		conn = JDBCUtil.getConnection();
		sql = "SELECT * FROM aboard WHERE seq=?";
		
		BoardVO board = new BoardVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				board.setCnt(rs.getInt("cnt"));
				board.setContent(rs.getString("content"));
				board.setDepth(rs.getInt("depth"));
				board.setNickname(rs.getString("nickname"));
				board.setFamily(rs.getInt("family"));
				board.setGroup_seq(rs.getInt("group_seq"));
				board.setRegdate(rs.getDate("regdate"));
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setUserid(rs.getString("userid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);			
		}
		return board;
	}
	
	public ArrayList<BoardVO> getBoardList(){
		conn = JDBCUtil.getConnection();
		sql = "SELECT * FROM aboard ORDER BY family DESC, group_seq";
		ArrayList<BoardVO> board = new ArrayList<BoardVO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setCnt(rs.getInt("cnt"));
				vo.setContent(rs.getString("content"));
				vo.setDepth(rs.getInt("depth"));
				vo.setNickname(rs.getString("nickname"));
				vo.setFamily(rs.getInt("family"));
				vo.setGroup_seq(rs.getInt("group_seq"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setUserid(rs.getString("userid"));
				vo.setParent(rs.getString("parent"));
				board.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(conn, pstmt);			
		}
		return board;
	}
	
	public ArrayList<BoardVO> list(int page, int interval) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		StringBuilder sql = new StringBuilder(100);
		sql.append("select b.* ");
		sql.append("from ( select rownum ro, a.* ");
		sql.append("       from (select * ");
		sql.append("             from aboard ");
		sql.append("             order by TO_NUMBER(family) DESC, TO_NUMBER(group_seq) ");
		sql.append("            ) a ");
		sql.append("     ) b ");
		sql.append("where ro>= ? and ro <= ? ");
		try {
			con = JDBCUtil.getConnection();
			st = con.prepareStatement(sql.toString());
			int start = 1; // 한페이지에 보여줄 게시글 시작 번호
			int end = interval; // 한페이지에 보여줄 게시글 마직막 번호
			// 페이지에 따른 글번호 계산
			if (page > 1) {
				start = (page - 1) * interval + 1;
				//end = start + interval - 1;
				end = page * interval;
			}
			st.setInt(1, start);
			st.setInt(2, end);
			rs = st.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCnt(rs.getInt("cnt"));
				vo.setContent(rs.getString("content"));
				vo.setDepth(rs.getInt("depth"));
				vo.setNickname(rs.getString("nickname"));
				vo.setFamily(rs.getInt("family"));
				vo.setGroup_seq(rs.getInt("group_seq"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setUserid(rs.getString("userid"));

				list.add(vo);
			}
		} finally {
			JDBCUtil.close(con,st,rs);
		}
		return list;
	}	
	
	public int listCount() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = JDBCUtil.getConnection();
			String sql = "select count(*) cnt from aboard ";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} finally {
			JDBCUtil.close(con,st,rs);
		}
		return count;
	}


}
