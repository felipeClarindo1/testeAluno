
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 String tokenUrl = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token/recuperaAlunos";
         String serviceUrl = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/recuperaAlunos";

         String tokenPostUrl = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token/gravaResultado";
         String servicePostUrl = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/gravaResultado";
         
         String usuario = "mentor";
         String senha = "123456";
         
         Servico service = new Servico();
         
         String token = service.getToken(tokenUrl, usuario, senha);
         String responseService = service.getInformation(serviceUrl, token);
         
         String tokenPost = service.postToken(tokenPostUrl, usuario, senha);
         
         service.postInformation(servicePostUrl, tokenPost, responseService);
         


	}

}
