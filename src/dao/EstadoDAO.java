package dao;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Estado;



public class EstadoDAO {
    
    public static List<Estado> getEstados(){
        
        String sql = "SELECT codigo, nome FROM estados ORDER BY nome"; 
        ResultSet rs = Conexao.consultar( sql );
        List<Estado> Lista = new ArrayList<>();
        if( rs != null){
            
            try {
                
               while(rs.next()){
                   Estado est = new Estado();
                   est.setCodigo( rs.getInt("codigo"));
                   est.setNome( rs.getString("nome"));
                   Lista.add(est);
               } 
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
        }
        
        return Lista;
        
    }
}
