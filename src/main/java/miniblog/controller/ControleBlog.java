package miniblog.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniblog.model.BlogMensagem;

@WebServlet(name = "ControleBlog", urlPatterns = {"/ControleBlog"})
public class ControleBlog extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {    
        
        String usuarioSession = (String)request.getSession().getAttribute("usuario"); 
        String excluir = request.getParameter("idchave");            
        
        if( usuarioSession == null){
            String nome = request.getParameter("nome");
            if(nome == null){
                    request.getRequestDispatcher("/miniblog/login.jsp").forward(request, response);
                    return;
                }

            if(nome.length() < 3 || nome.trim().length() == 0){
                request.setAttribute("ERRO_BEAN",
                        new String("ERRO: <b>O Nome não pode ser vazio e inferior a 3 caracteres.</b>"));
                request.getRequestDispatcher("/miniblog/login.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("usuario", nome);
        }

        // Contexto de AplicaÃ§Ã£o  usar getServletContexto. Para contexto de sessao usar request.getSession()
        ServletContext contextoObjetoBean = getServletContext();
        
        // carrega a lista de â€œmensagensâ€?, cria a lista se necessÃ¡rio.
        ArrayList<BlogMensagem> listaMsg = (ArrayList) contextoObjetoBean.getAttribute("BEAN_LISTA");     
        if (listaMsg == null) {
           listaMsg = new ArrayList();
        }
        
        // Recuperar a mensagem do formuario
        if(excluir == null){
            String msg = request.getParameter("mensagem");
            if (msg==null ){ // nao foi enviado o parametro. Retorna ao Blog 
                request.getRequestDispatcher("/miniblog/blog.jsp").forward(request, response);
                return;
            }
            // testa se valor da mensagem estah em branco...
            if (msg.trim().length() == 0) {
                request.setAttribute("ERRO_BEAN",
                        new String("ERRO: <b>A mensagem deve ser preenchida.</b>"));
                request.getRequestDispatcher("/miniblog/blog.jsp").forward(request, response);
                return;
            }
            
            // definir/criar o objeto de dados (BlogMensagem)
            BlogMensagem blogMsg = new BlogMensagem();
            blogMsg.setMensagem(msg);
        
             // adiciona o objeto "blogMsg" na lista
            blogMsg.setId(recuperaUltimoId(listaMsg));
            blogMsg.setUsuario(usuarioSession);
            listaMsg.add(blogMsg);
        }
         
        if(excluir != null){
            int num = Integer.parseInt(excluir);
            int i = 0;
            for (BlogMensagem listaMsgF : listaMsg) {
                System.out.println("id atual:" + listaMsgF.getId());
                if(listaMsgF.getId() == num ){
                    if(listaMsgF.getUsuario().equals(usuarioSession)){
                        listaMsg.remove(i); 
                        request.setAttribute("SUCCESS_BEAN",
                        new String("SUCESSO: <b>Mensagem excluida com sucesso!.</b>"));
                        request.getRequestDispatcher("/miniblog/blog.jsp").forward(request, response);
                    } else {
                        request.setAttribute("ERRO_BEAN",
                        new String("ERRO: <b>Você só pode apagar mensagens enviadas por você.</b>"));
                        request.getRequestDispatcher("/miniblog/blog.jsp").forward(request, response);
                    }                 
                }
                i++;
            }
        }
        
        // salvar a lista em tempo de sessao (como bean)
        contextoObjetoBean.setAttribute("BEAN_LISTA", listaMsg);
      
        // despacho para a pÃ¡gina de blog
        request.getRequestDispatcher("/miniblog/blog.jsp").forward(request, response);
    }

    private int recuperaUltimoId(ArrayList<BlogMensagem> listaMsg) {
        int ultimoIndice = listaMsg.size();
        int idResposta = 1;
        if (ultimoIndice > 0) {
            ultimoIndice = ultimoIndice - 1;
            BlogMensagem blogMsg = listaMsg.get(ultimoIndice);
            idResposta = blogMsg.getId() + 1;
        }
        return idResposta;
    }
}
