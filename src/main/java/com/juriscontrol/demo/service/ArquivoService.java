// // package com.juriscontrol.demo.service;

// // import java.io.IOException;
// // import java.nio.file.Files;
// // import java.nio.file.Path;
// // import java.nio.file.Paths;
// // import java.util.ArrayList;
// // import java.util.List;

// // import org.springframework.stereotype.Service;
// // import org.springframework.web.multipart.MultipartFile;

// // import com.juriscontrol.demo.config.ArquivoConfig;
// // import com.juriscontrol.demo.dto.DocumentoDTO.ArquivoDTO.ArquivoDTO;

// // @Service
// // public class ArquivoService {
    
// //     private final Path diretorio;

// //     public ArquivoService(ArquivoConfig arquivoConfig) {
// //         this.diretorio = Paths.get(arquivoConfig.getCarregarDiretorio()).toAbsolutePath().normalize();
// //     }

// //     public ArquivoDTO carregarArquivo(MultipartFile anexo) throws IOException {
// //         if (anexo == null || anexo.isEmpty()) {
// //             throw new IOException("O arquivo anexado está vazio ou é inválido.");
// //         }
        
// //         if (!Files.exists(this.diretorio)) {
// //             Files.createDirectories(diretorio);
// //         }

// //         Path caminhoArquivo = diretorio.resolve(anexo.getOriginalFilename());

// //         if(Files.exists(caminhoArquivo)) {
// //             throw new IOException("Arquivo " + caminhoArquivo.getFileName() + " já existe.");
// //         }

// //         Files.copy(anexo.getInputStream(), caminhoArquivo);

// //         return new ArquivoDTO(
// //             anexo.getOriginalFilename(),
// //             anexo.getContentType(),
// //             anexo.getSize(),
// //             caminhoArquivo.toUri().toString()
// //         );
// //     }

// //     //Método personalizado
// //     public List<ArquivoDTO> carregarVariosArquivos(List<MultipartFile> anexos) throws IOException {
// //         List<ArquivoDTO> arquivos = new ArrayList<>();

// //         for(MultipartFile anexo : anexos) {
// //             arquivos.add(carregarArquivo(anexo));
// //         }

// //         return arquivos;
// //     }

// //     public Path buscarArquivo(String nomeDocumento) {
// //         return diretorio.resolve(nomeDocumento).normalize();
// //     }

// //     public List<ArquivoDTO> buscarTodosArquivos() throws IOException {
// //         List<Path> listaCaminhos = Files.list(diretorio)
// //             .filter(Files::isRegularFile)
// //             .toList();
        
// //         List<ArquivoDTO> listaArquivos = new ArrayList<>();
// //         for (Path caminhoArquivo : listaCaminhos) {
// //             listaArquivos.add(new ArquivoDTO(
// //                 caminhoArquivo.getFileName().toString(),
// //                 Files.probeContentType(caminhoArquivo),
// //                 Files.size(caminhoArquivo),
// //                 caminhoArquivo.toUri().toString()
// //             ));
// //         }
// //         return listaArquivos;
// //     }

// //     //Método personalizado para ler e baixar
// //     public byte[] lerArquivo(Path caminhoArquivo) throws IOException {
// //         return Files.readAllBytes(caminhoArquivo);
// //     }

// //     public ArquivoDTO atualizarArquivo(MultipartFile novoAnexo, String nomeDocumento) throws IOException {
// //         excluirArquivo(nomeDocumento);
// //         return carregarArquivo(novoAnexo);
// //     }

// //     public void excluirArquivo(String nomeDocumento) throws IOException {
// //         Path caminhoArquivo = buscarArquivo(nomeDocumento);
// //         Files.deleteIfExists(caminhoArquivo);
// //     }

//     /*
//     public InputStream baixarArquivo(String nomeDocumento) throws IOException {
//         Path caminhoCompleto = caminhoArquivo.resolve(nomeArquivo); // Localiza o arquivo no diretório
        
//         if (!Files.exists(caminhoCompleto) || Files.isDirectory(caminhoCompleto)) {
//             throw new FileNotFoundException("Arquivo não encontrado: " + nomeArquivo);
//         }
        
//         return new FileInputStream(caminhoCompleto.toFile()); 
//     }
//     */
// }
