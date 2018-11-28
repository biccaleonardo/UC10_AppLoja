
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Cidade;
import model.Cliente;
import model.Estado;

/**
 *
 * @author 181710077
 */
public class ClienteDAO {
    
    public static void inserir(Cliente cliente){
        String data = "" + cliente.getNascimento().get(Calendar.YEAR)
                + "-" + (cliente.getNascimento().get(Calendar.MONTH)+1)
                + "-" + cliente.getNascimento().get(Calendar.DAY_OF_MONTH);
        String sql ="INSERT INTO clientes "
                + "(nome, telefone, cpf, salario, filhos, casado, sexo, dataNascimento, codCidade) VALUES("
                + "' "+cliente.getNome()        +"' , "
                + "' "+cliente.getTelefone()    +"' , "
                + "' "+cliente.getCpf()         +"' , "
                + "  "+cliente.getSalario()     +"  , "
                + "  "+cliente.isTemFilhos()    +"  , "
                + "  "+cliente.isCasado()       +"  , "
                + "' "+cliente.getSexo()        +"' , "
                + "' "+data                     +"' , "
                + "' "+cliente.getCidade().getCodigo()                
                + " ) " ;
        Conexao.executar(sql);
                
               
    } 
    
    public static void editar(Cliente cliente){
        String data = "" + cliente.getNascimento().get(Calendar.YEAR)
                + "-" + (cliente.getNascimento().get(Calendar.MONTH)+1)
                + "-" + cliente.getNascimento().get(Calendar.DAY_OF_MONTH);
        String sql ="UPDATE clientes set "
                + "nome      = ' "+cliente.getNome()        +"' , "
                + "telefone  = ' "+cliente.getTelefone()    +"' , "
                + "cpf       = ' "+cliente.getCpf()         +"' , "
                + "salario   =   "+cliente.getSalario()     +"  , "
                + "filhos    =   "+cliente.isTemFilhos()    +"  , "
                + "casado    =   "+cliente.isCasado()       +"  , "
                + "sexo      = ' "+cliente.getSexo()        +"' , "
                + "data      = ' "+data                     +"' , "
                + "codcidade = ' "+cliente.getCidade().getCodigo()                
                + " WHERE codigo = " + cliente.getCodigo() ;
        Conexao.executar(sql);
                
               
    }
    
    public static void excluir(Cliente cliente){
        
        String sql ="DELETE FROM clientes"
                + "WHERE codigo = " +cliente.getCodigo();
                
        Conexao.executar(sql);
                
               
    }
    
    public static List<Cliente> getClientes() {
        List<Cliente> lista = new ArrayList<>();
        
        String sql = "SELECT c.codigo, c.nome, c.telefone, c.cpf, "
                + "c.salario, c.filhos, c.casado, c.datanascimento, "
                + "c.sexo, m.codigo, m.nome, e.codigo, e.nome "
                + "FROM clientes c "
                + "INNER JOIN cidades m ON m.codigo = c.codCidade "
                + "INNER JOIN estados e ON e.codigo = m.codEstado "
                + "ORDER BY c.nome ";
        
                ResultSet rs = Conexao.consultar(sql);
                if( rs != null){
                    try {
                        
                        while( rs.next()){
                            Estado estado = new Estado();
                            estado.setCodigo( rs.getInt(12));
                            estado.setNome(rs.getString(13));
                            
                            Cidade cidade = new Cidade();
                            cidade.setCodigo(rs.getInt(10));
                            cidade.setNome(rs.getString(11));
                            cidade.setEstado(estado);
                            
                            Cliente cliente = new Cliente(); 
                            cliente.setCodigo( rs.getInt(1));
                            cliente.setNome(rs.getString(2)); 
                            cliente.setTelefone(rs.getString(3)); 
                            cliente.setCpf(rs.getString(4)); 
                            cliente.setSalario(rs.getDouble(5)); 
                            cliente.setTemFilhos(rs.getBoolean(6)); 
                            cliente.setCasado(rs.getBoolean(7)); 
                            cliente.setSexo(rs.getString(8));
                            
                            Calendar nascimento = Calendar.getInstance();
                            
                            
                            
                            
                        }
                        
                    } catch (Exception e) {
                        
                    }
                }
        
        return lista;
       
    }
    
}
