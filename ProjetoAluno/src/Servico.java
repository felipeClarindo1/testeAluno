import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Servico {
	
	//Pega token do serviço
	public  String getToken(String url, String usuario, String senha) throws Exception {
		 OkHttpClient client = new OkHttpClient();
		 
         Request request = new Request.Builder()
           .url(url)
           .get()
           .addHeader("usuario", usuario)
           .addHeader("senha", senha)
           .build();

         try (Response response = client.newCall(request).execute()) {
             if (response.isSuccessful()) {
                 String jsonResponse = response.body().string();
                 return jsonResponse;
             } else {
                 System.out.println("Erro na requisição: " + response.code());
                 return "";
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
		return "";
	        
	}
	
	//Pega token para gravar e enviar resultados
	public  String postToken(String url, String usuario, String senha) throws Exception {
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("usuario", usuario)
				.addHeader("senha", senha)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				String jsonResponse = response.body().string();
				return jsonResponse;
			} else {
				System.out.println("Erro na requisição: " + response.code());
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
		
	}

	//Pega as informações dos alunos
	public static String getInformation(String service, String token) throws Exception {
		 OkHttpClient client = new OkHttpClient();
		 
         MediaType mediaType = MediaType.parse("application/json");
         RequestBody body = RequestBody.create(mediaType, "");
         Request request = new Request.Builder()
           .url(service)
           .post(body)
           .addHeader("content-type", "application/json")
           .addHeader("token", token)
           .build();
         
         try (Response response = client.newCall(request).execute()) {
             if (response.isSuccessful()) {
                 String jsonResponse = response.body().string();
                
                 return  processJson(jsonResponse);
             } else {
                 System.out.println("Erro na requisição: " + response.code());
                 return "";
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
		return "";
	}
	
	//Envia os resultados
	public static String postInformation(String service, String token,String jsonResultado) throws Exception {
		 OkHttpClient client = new OkHttpClient();
		 
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonResultado);
        Request request = new Request.Builder()
          .url(service)
          .post(body)
          .addHeader("content-type", "application/json")
          .addHeader("token", token)
          .build();
        
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                
                return jsonResponse;
            } else {
                System.out.println(response.body().string());
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "";
	}
	
	//Processa o json com as informações do alunos criando os objetos para tratalos
	private static String processJson(String json) {
		
		Gson gson = new Gson();
        Resultado[] resultados = gson.fromJson(json, Resultado[].class);
        
        List<Aluno> listAlunos = new ArrayList<>();

        for (Resultado resultado : resultados) {
            for (Aluno aluno : resultado.getAlunos()) {
                Aluno alunoResultado = new Aluno();
                alunoResultado.setCOD(aluno.getCOD());
                alunoResultado.setNOME(aluno.getNOME());
                alunoResultado.setTOTAL_AULAS(aluno.getTOTAL_AULAS());
                alunoResultado.setNota(aluno.getNota());
                
                listAlunos.add(alunoResultado);
            }
        }
    
    
		
		return resultadoJson(listAlunos);
	}
	
	//tratas as informações e gera um json para envio
	private static String resultadoJson(List<Aluno> alunos) {
		
		List<ResultadoWrapper> listResultadoAlunos = new ArrayList<>();
		double media = 0D;
		int frequencia = 0;
		for(Aluno aluno : alunos) {
			ResultadoAluno resultadoAluno = new ResultadoAluno();
			 for(Nota nota : aluno.getNota()) {
				 media += nota.getNOTA();
				 frequencia += nota.getFALTAS();
			 }
			 media = media /  aluno.getNota().size();
			 frequencia = 100 - ((100 * frequencia) / aluno.getTOTAL_AULAS());
			 
			 resultadoAluno.setCOD_ALUNO(aluno.getCOD());
			 resultadoAluno.setMEDIA(String.format("%.2f", media));
			 resultadoAluno.setRESULTADO(frequencia);
			 
			 ResultadoWrapper wrapper = new ResultadoWrapper();
		        wrapper.setResultadoAluno(resultadoAluno);
		        
			 listResultadoAlunos.add(wrapper);
		}
		
		
		
		Gson gson = new Gson();
        String json = gson.toJson(listResultadoAlunos);
        gerarRelatorio(alunos);
		
		return json;
	}
	
	//gera o relatório
	private static void gerarRelatorio(List<Aluno> alunos) {
		double media = 0D;
		int frequencia = 0;
		int faltas = 0;
		StringBuilder sb = new StringBuilder();
		for(Aluno aluno : alunos) {
			 for(Nota nota : aluno.getNota()) {
				 media += nota.getNOTA();
				 frequencia += nota.getFALTAS();
			 }
			 media = media /  aluno.getNota().size();
			 faltas = frequencia;
			 frequencia = 100 - ((100 * frequencia) / aluno.getTOTAL_AULAS());
			 
			 sb.append("Nome: ").append(aluno.getNOME()).append("; ");
			 sb.append("Notas: ");
			 for(Nota nota : aluno.getNota()) {
				 sb.append(nota.getNOTA()).append(", ");
			 }
			 sb.deleteCharAt(sb.length() - 1);
			 sb.append("; Total de Faltas: ").append(faltas).append("; Média: ").append(String.format("%.2f", media)).append("; Resultado: ").append(frequencia).append(";\n");
		}
		
		
		// Coloque o endereço onde vai ser salvo o relatório
//		File file = new File("C:\\exmplo.txt");
		File file = new File("");
		
		 try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
	            bw.write(sb.toString());
	        } catch (IOException e) {
	        }
		
	}
}
