package com.modoo.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.modoo.cg.dto.Dto;
import com.modoo.cg.util.Constant;
import com.mysql.cj.jdbc.BlobFromLocator;

public class Dao {
	DataSource dataSource;
	JdbcTemplate template=null;
	
	public Dao() {
		template=Constant.template;
	}
	
	public Dto constentView(String strId) {
		//int strid = Integer.parseInt(strId);
		upHit(strId);
		String query="select * from free_board where id= "+strId;
		return template.queryForObject(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	public void write(final String name, final String title, final String content) {
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				String query = "insert into free_board (name, title, content, hit, cgroup, step, indent) values (?, ?, ?, 0, 0, 0, 0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				
				
				return pstmt;
			}
		});
		
	}
	
	//게시판 글
	public ArrayList<Dto> listsize(final int indexstart , final int pagesize){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board order by id desc LIMIT "+(pagesize)+" OFFSET "+indexstart;
		System.out.println("pagesize+indexstart : " + (pagesize+indexstart));
		System.out.println("indexstart : " + indexstart);
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	//게시글 카운트
	public ArrayList<Dto> list(){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board order by cgroup desc, step asc";
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
		
	
	//게시판 제목 검색
	public ArrayList<Dto> listtitleSearch(final int indexstart , final int pagesize ,final String keyword ){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board where title like '%"+keyword+"%' order by id desc LIMIT "+(pagesize+indexstart)+" OFFSET "+indexstart;
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	
	//게시판 제목 검색 카운트
	public ArrayList<Dto> listtitlesize(final String keyword ){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board where title like '%"+keyword+"%'";
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	
	
	
	public void modify(final String id, final String  name, final String title, final String content) {
		String query = "update free_board set name = ?, title = ?, content = ? where id = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, name);
				ps.setString(2, title);
				ps.setString(3, content);
				ps.setInt(4, Integer.parseInt(id));
			}
		});
		
	}
	
	public void delete( final String strId) {
		
		String query = "delete from free_board where id = ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, strId);
			}
		});
	}
	
	public Dto reply_view(String strId) {
		String query = "select * from free_board where id = " + strId;
		return template.queryForObject(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	public void reply(final String id, final String name, final String title, final String content, final String cgroup, final String step, final String indent) {
		// TODO Auto-generated method stub	
		String query = "insert into free_board (id, name, title, content, cgroup, step, indent) values (?, ?, ?, ?, ? ,? ,?)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, title);
				ps.setString(4, content);
				ps.setInt(5, Integer.parseInt(cgroup));
				ps.setInt(6, Integer.parseInt(step) + 1);
				ps.setInt(7, Integer.parseInt(indent) + 1);
			}
		});
	}
	
	private void replyShape( final String strGroup, final String strStep) {
		String query = "update free_board set step = step + 1 where cgroup = ? and step > ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strGroup));
				ps.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
	
	private void upHit(final String id) {
		System.out.println(id);
		String query="update free_board set hit=hit+1 where id=?";
		template.update(query, new PreparedStatementSetter(){
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1, Integer.parseInt(id));
			}
		});
	}
	public void register(final String id, final String nickname, final String email) {
		System.out.println("register()");
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				String query = 
						"insert into register (id, nickname, email, create_date, grade, exp) select 'test', ?, ?, now(), '슛돌이', 0 from dual where not exists (select  id from register where id='gom') ";
				PreparedStatement pstmt = con.prepareStatement(query);
				/*pstmt.setString(1, id);*/
				pstmt.setString(1, nickname);
				pstmt.setString(2, email);
				/*pstmt.setString(3, id);*/
				System.out.println(query);
				
				return pstmt;
			}
		});
	}
}
