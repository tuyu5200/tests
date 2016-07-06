package util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuyu on 2016/6/17 15:42 .
 */
public class JDBCutil {

    private Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String psw = "tuyu";
    private PreparedStatement pst;
    private Statement stmt;
    private ResultSet rt;
    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    //连接数据库
    //加载驱动
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public JDBCutil() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            System.out.println("驱动加载失败");
            e.printStackTrace();
        }
    }
//连接数据库
    public void getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, psw);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //插入数据
    public void doInsert(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("db.excuteInsert:"+e.getMessage());
        }
    }

//    查询(Class t  反射把字节码传过了，查看是哪个类型)
    public <T> List<T> QueryInfo(String sql,List<Object> paras,Class<T> tClass){
        //定义返回数据的集合
        List<T> list=new ArrayList<T>();
//        获取连接
        this.getConnection();
        int index=1;
        try {
            pst=conn.prepareStatement(sql);
            if (paras != null && paras.size() > 0) {
                for (int i = 0; i < paras.size(); i++) {

                    pst.setObject(index++, paras.get(i));
                }
            }
            ResultSet rs=pst.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();
            int length=metaData.getColumnCount();//拿到总列数
            while (rs.next()) {
                //通过反射机制创建一个实例
                T t=tClass.newInstance();
                for (int i = 0; i < length; i++) {
                    String colunName=metaData.getCatalogName(i);  //拿到某列的名字
                    Object colunValue= rs.getObject(colunName);  //拿到值

                    if (colunName == null) {
                        colunName = "";

                    }
                    Field field = tClass.getDeclaredField(colunName);
                    field.setAccessible(true);   //给出权限
                    field.set(t,colunValue);
                }
                list.add(t);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        return null;
    }

    //执行sql
    public boolean excuteInsertUpdateDelete(String sql, List<Object> paras) {
        //拿到连接
        this.getConnection();
        int index=1;
        int  result=0;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            if (paras != null && paras.size() > 0) {
                pst.clearParameters();
                for (int i = 0; i < paras.size(); i++) {
                    pst.setObject(index++, paras.get(i));
                }
               pst.executeUpdate();  //返回影响数据的行数
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0?true:false;

    }
    //关闭数据库
    public void closeConn(){

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
