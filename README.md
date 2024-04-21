# Consultor de Musicas ✨


O Consultor de Músicas é uma aplicação Java  para explorar e descobrir informações sobre seus artistas e músicas favoritos, tudo isso através da poderosa API do Last.fm.


Seja para conhecer mais sobre um artista específico, cadastrar novas músicas ou simplesmente explorar as últimas tendências musicais, o Consultor de Músicas oferece uma jornada envolvente e informativa, tudo isso ao alcance de suas mãos.


## Sumário 📋


1. [Sobre o Projeto](#sobre-o-projeto)
    - [Principais Funcionalidades](#principais-funcionalidades)
    - [Classes Principais](#classes-principais)
    - [Tecnologias Utilizadas](#tecnologias-utilizadas)
    - [Variáveis de Ambiente](#variáveis-de-ambiente)
2. [Como Executar](#como-executar)
3. [Contribuindo](#contribuindo)
4. [Licença](#licença)


## Sobre o Projeto


O Consultor Musica é uma aplicação Java desenvolvida no framework Spring Boot com auxilio do Maven, tento como objetivo interagir com uma API externa fornecendo informações detalhadas sobre msucias e artistas. Abaixo, ofereço uma descrição técnica das principais funcionalidades, classes e bibliotecas utilizadas neste programa:


## Principais Funcionalidades:


- **Buscar Artistas:** Explore informações detalhadas sobre artistas, incluindo nome, categoria, reproduções e bibliografia.


- **Cadastrar Músicas:** Adicione novas músicas para artistas encontrados na busca.


- **Listar Músicas:** Veja todas as músicas cadastradas no sistema, organizadas por artista.


- **Buscar Músicas por Artista:** Descubra e liste todas as músicas de um artista específico.


- **Pesquisar Dados sobre um Artista:** Obtenha informações detalhadas sobre um artista, incluindo suas melhores músicas.


## Classes Principais:


**Principal (Principal.java):**


Esta classe é responsável por controlar a interação com o usuário e o fluxo de execução do programa.
Utiliza instâncias da classe ConsumoApi para realizar requisições à API do Last.fm e da classe ConverteDados para converter os dados retornados pela API em objetos Java.


**ConsumoApi (ConsumoApi.java):**


Esta classe encapsula a lógica para realizar chamadas HTTP à API do Last.fm e obter os dados correspondentes.
Utiliza a classe HttpClient do pacote java.net.http para fazer requisições HTTP de forma assíncrona.
Retorna os dados obtidos da API no formato de uma string JSON.


**ConverteDados (ConverteDados.java):**


Esta classe é responsável por converter os dados JSON retornados pela API em objetos Java.
Utiliza a biblioteca Jackson, uma popular biblioteca de processamento JSON em Java, para fazer a conversão dos dados.
Implementa métodos para realizar a conversão de uma string JSON para objetos Java de um tipo específico.


**ArtistaRepository (ArtistaRepository.java):**


Esta interface define métodos para acessar e manipular os dados da entidade Artista no banco de dados.
Estende a interface JpaRepository do Spring Data JPA, que fornece operações CRUD básicas para a entidade Artista.


**Artista (Artista.java):**


Esta classe representa um artista de música.
- Atributos:
  - `id`: Identificador único do artista.
  - `nome`: Nome do artista.
  - `categoria`: Categoria do artista (SOLO, DUPLA, ou BANDA).
  - `reproducoes`: Número de reproduções do artista.
  - `bibliografia`: Biografia do artista.
  - `musicas`: Lista de músicas associadas ao artista.
- Mapeamento JPA:
  - Classe mapeada para a tabela `artistas`.
  - Relacionamento One-to-Many com a classe `Musica`.


**Musica (Musica.java):**


Esta classe representa uma música.
- Atributos:
  - `id`: Identificador único da música.
  - `nome`: Nome da música.
  - `reproducoes`: Número de reproduções da música.
  - `artistaNome`: Nome do artista associado à música.
- Mapeamento JPA:
  - Classe mapeada para a tabela `musicas`.
  - Relacionamento Many-to-One com a classe `Artista`.


**CategoriaArtista (CategoriaArtista.java):**


Esta enumeração representa as categorias possíveis de artistas (SOLO, DUPLA, ou BANDA).
- Atributo:
  - `categoriaApiLastFm`: Categoria da API Last.fm.


**RecordGeneric (RecordGeneric.java):**


Esta classe é responsável por mapear a estrutura do JSON retornado pela API do Last.fm.
- Classes internas:
  - `DadosMusica`: Representa os dados de uma música.
  - `RecArtista`: Representa os dados de um artista.
  - `RecStats`: Representa as estatísticas de um artista.
  - `RecBio`: Representa a biografia de um artista.


## Tecnologias Utilizadas


- Java
- Spring Boot
- Hibernate
- PostgreSQL
- Maven
- Jackson JSON


## Variáveis de Ambiente:


- `LASTFM_APIKEY`: Chave de API necessária para acessar a API do Last.fm. Certifique-se de configurá-la antes de executar o aplicativo.
- `spring.datasource.url`: URL de conexão com o banco de dados PostgreSQL. Substitua `${DB_HOST}` e `${DB_NAME_MUSICAS}` pelos valores correspondentes ao seu ambiente.
- `spring.datasource.username`:${DB_USER} - Nome de usuário para acessar o banco de dados PostgreSQL.
- `spring.datasource.password`:${DB_PASSWORD} - Senha para acessar o banco de dados PostgreSQL.




## Como Executar


Certifique-se de ter as dependências necessárias instaladas, como o Maven e o PostgreSQL. Consulte o arquivo `application.properties` para configurar o banco de dados e outras propriedades do Spring Boot.


Para executar o arquivo .jar do projeto, deverá ter o JDK 17 instalado em seu sistema.
 Em seguida, siga estas etapas:


1. Abra o terminal (no Windows, você pode abrir o prompt de comando ou o PowerShell).


2. Pelo prompt navegue até o diretório onde está localizado o arquivo .jar,dentro do repositório, usando o comando `cd`.                  
Por exemplo:
```
cd  C:\Users\Mario\Documents\consultorFipe\out\artifacts\consultorFipe_jar
```


3. Uma vez dentro do diretório correto, execute o seguinte comando para iniciar a aplicação:
```
java -jar ConsultorMusicas.jar
```


Após executar esse comando, a aplicação deve iniciar normalmente. Certifique-se de que todas as dependências necessárias estão corretamente configuradas e que o arquivo .jar foi está no diretório correto.


<h3>Se preferir você pode carregar, compilar e executar o projeto em uma IDE de preferência.  


## Contribuindo


Instruções para contribuir com o projeto, seja através de sugestões, relatórios de bugs, solicitações de funcionalidades ou até mesmo envio de código.


1. Faça um fork do projeto.
2. Crie uma nova branch (`git checkout -b feature/nova-feature`).
3. Faça commit das suas mudanças (`git commit -am 'Adicione uma nova feature'`).
4. Faça push para a branch (`git push origin feature/nova-feature`).
5. Crie um novo Pull Request.


## Licença


MIT License


Copyright (c) 2024 Evaldo Junior


Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:


The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.


THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



