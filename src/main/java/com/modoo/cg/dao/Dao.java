package com.modoo.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.modoo.cg.dto.Dto;
import com.modoo.cg.dto.MailDto;
import com.modoo.cg.dto.RegisterDto;
import com.modoo.cg.dto.ReppDto;
import com.modoo.cg.util.Constant;


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
	
	//寃뚯떆�뙋 湲�
	public ArrayList<Dto> listsize(final int indexstart , final int pagesize){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board order by id desc LIMIT "+(pagesize)+" OFFSET "+indexstart;
		System.out.println("pagesize+indexstart : " + (pagesize+indexstart));
		System.out.println("indexstart : " + indexstart);
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	//寃뚯떆湲� 移댁슫�듃
	public ArrayList<Dto> list(){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board order by cgroup desc, step asc";
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
		
	
	//寃뚯떆�뙋 �젣紐� 寃��깋
	public ArrayList<Dto> listtitleSearch(final int indexstart , final int pagesize ,final String keyword ){
		String query = "select id, name, title, content, hit, cgroup, step, indent from free_board where title like '%"+keyword+"%' order by id desc LIMIT "+(pagesize+indexstart)+" OFFSET "+indexstart;
		return (ArrayList<Dto>) template.query(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	
	//寃뚯떆�뙋 �젣紐� 寃��깋 移댁슫�듃
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
	
	
	public ArrayList<ReppDto> replySn(String num){
		String query = "select * from repply_t where border_id = "+ num;
		return (ArrayList<ReppDto>) template.query(query, new BeanPropertyRowMapper<ReppDto>(ReppDto.class));
	}
	
	
	
	public int replyw(final String bid, final String border_id, final String content, final int parent, final int depth, final int orderid) {
		// TODO Auto-generated method stub	
		String query = "insert into repply_t (bid, border_id, content, parent, depth, orderid ) values (?, ?, ?, ?, ? ,? )";
		int number=template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bid);
				ps.setString(2, border_id);
				ps.setString(3, content);
				ps.setInt(4, parent);
				ps.setInt(5, depth);
				ps.setInt(6, orderid);
			}
		});
		
		return  number;
	}
	
	/*create table repply_t(
			num integer primary key not null auto_increment,
			bid varchar(50),
			border_id varchar(50),
			content text,
			parent int(50),
			depth int(50),
			orderid int(50)

		);*/

	
	
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
	
	//==================================================================================
	//�뿬湲곗꽌遺��꽣�뒗 履쎌� 援ы쁽遺�遺꾩엯�땲�떎. 二꾩넚�빀�땲�떎留� 嫄대뱶由ъ� 留먯븘二쇱떆二�
	//==================================================================================
	//�떦�떊�쓣 �뙆.愿�.�븷.吏�.�룄 紐⑤Ⅴ�땲源뚯슂
	//==================================================================================
	
	public ArrayList<MailDto> mailList(){
		String query = "select post, send, contents, post_get, send_get, post_date, send_date, post_del, send_del from mail order by num desc";
		return (ArrayList<MailDto>) template.query(query, new BeanPropertyRowMapper<MailDto>(MailDto.class));
	}
	
	public ArrayList<MailDto> mlistsize(final int indexstart , final int pagesize){
		String query = "select post, send, contents, post_get, send_get, post_date, send_date, post_del, send_del from mail order by num desc LIMIT "+(pagesize+indexstart)+" OFFSET "+indexstart;
		return (ArrayList<MailDto>) template.query(query, new BeanPropertyRowMapper<MailDto>(MailDto.class));
	}
	
	public void mailwrite(final String post, final String send, final String contents) {
		System.out.println("mailwrite(on)");
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				String query = "insert into mail (post, send, contents, post_get, send_get, post_date, send_date, post_del, send_del) values (?, ?, ?, '0', '0', now(), now(), '0', '0')";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, post);
				pstmt.setString(2, send);
				pstmt.setString(3, contents);
				
				return pstmt;
			}
		});
	}
	//==================================================================================
	//履쎌� 援ы쁽遺�遺� �삤�굹��猷�
	//==================================================================================
	
	public void register(final String id, final String nickname, final String email) {
		System.out.println("register()");
		System.out.println("email:"+email );
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				String query = 
						"insert into register (id, nickname, email, create_date, grade, exp) select ?, ?, ?, now(), '슟돌이', 0 from dual where not exists (select  id from register where id=?) ";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, nickname);
				pstmt.setString(3, email);
				pstmt.setString(4, id);
				System.out.println(query);
				
				return pstmt;
			}
		});
	}
	
	public ArrayList<RegisterDto> myinfo(final String id){
		System.out.println("myinfo()");
		String query = "select id, nickname, email, create_date, grade, exp from register";// where id="+id;
		
		System.out.println("myinfo(). id="+id);
		System.out.println("myinfo() query"+query);
		
		return (ArrayList<RegisterDto>) template.query(query, new BeanPropertyRowMapper<RegisterDto>(RegisterDto.class));
	}
}
