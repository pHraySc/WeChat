package com.sccc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;
import com.sccc.bean.Message;
import com.sccc.db.DBAccess;

/**
 * 
 * @author Sccc 和Message表相关的数据库操作
 * 
 */
public class MessageDao {
	/**
	 * 根据消息条件查询消息列表
	 * 
	 * @throws SQLException
	 */
	public List<Message> queryMessageList(String command, String description){
		List<Message> messageList = new ArrayList<Message>();
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = access.getSqlSession();
			Message message = new Message();
			//检索数据信息
			if(command != null && !"".equals(command.trim())){
				message.setCommand(command.trim());
			}
			if(description != null && !"".equals(description.trim())){
				message.setDescription(description.trim());
			}
			//通过sqlSession执行SQL语句
			messageList = sqlSession.selectList("Message.queryMessageList", message);//通过ID调用
			//获取执行SQL语句的结果
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
			sqlSession.close();		//最终关掉SqlSession
			}
		}
		return messageList;
	}
	
	/**
	 * 
	 * 单条删除
	 */
	public void deleteOne(int id){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = access.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.delete("Message.deleteOne", id);//通过ID调用
			//获取执行SQL语句的结果
			sqlSession.commit();
			//提交
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
			sqlSession.close();		//最终关掉SqlSession
			}
		}
	}
	
	public void deleteBatch(List<Integer> idList) {
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = access.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.delete("Message.deleteBatch", idList);//通过ID调用
			//获取执行SQL语句的结果
			sqlSession.commit();
			//提交
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
			sqlSession.close();		//最终关掉SqlSession
			}
		}
	}
//	public static void main(String[] args) {
//		MessageDao dao = new MessageDao();
//		dao.deleteOne(2);
//	}
	/**
	 * 根据消息条件查询消息列表
	 * 
	 * @throws SQLException
	 */
//	public List<Message> queryMessageList(String command, String description) {
//		List<Message> messageList = new ArrayList<Message>();
//		Connection conn = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager
//					.getConnection(
//							"jdbc:mysql://localhost:3306/micro_message?characterEncoding=utf8",
//							"root", "722799");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 不要用select * 会大大降低效率
//		StringBuilder sql = new StringBuilder(
//				"select ID, COMMAND, DESCRIPTION, CONTENT from MESSAGE where 1=1 ");
//		List<String> paramList = new ArrayList<String>();
//		if (command != null && !"".equals(command.trim())) { // 用""去equals
//			sql.append(" and COMMAND =?");
//			paramList.add(command);
//		}
//		if (description != null && !"".equals(description.trim())) { // 用""去equals
//			sql.append(" and DESCRIPTION like '%' ? '%' ");
//			paramList.add(description);
//		}
//		try {
//			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql
//					.toString());
//			for (int i = 0; i < paramList.size(); i++) {
//				ps.setString(i + 1, paramList.get(i));
//			}
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Message message = new Message();
//				messageList.add(message);
//				message.setId(rs.getInt("ID"));
//				message.setCommand(rs.getString("COMMAND"));
//				message.setDescription(rs.getString("DESCRIPTION"));
//				message.setContent(rs.getString("CONTENT"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return messageList;
//	}
}
