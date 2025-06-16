# box-flow

# box-flow

🧭 Roadmap Técnico: App Desktop de Caixa de Mercado (Java + JavaFX + Spring Boot)
🧱 1. Arquitetura do Projeto
🧩 Estilo Arquitetural:

Arquitetura MVC para o JavaFX

Backend desacoplado com Spring Boot (camada de serviços e repositórios)

Integração via chamada direta (uso local) ou via REST (opcional)

🔁 Camadas:

View (JavaFX) – Interface gráfica

Controller (JavaFX) – Lógica de interface

Service (Spring Boot) – Regras de negócio

Repository (Spring Data JPA) – Acesso a banco de dados

Entity – Mapeamento das tabelas

🧰 2. Stack de Tecnologias

Java 17+

JavaFX 17+ (com FXML + SceneBuilder)

Spring Boot 3+

Spring Data JPA (para persistência)

H2 (dev) e PostgreSQL ou MySQL (produção)

Lombok (facilita getters/setters)

Maven ou Gradle

Hibernate Validator (validações)

Flyway (migrações, opcional)

🗂️ 3. Estrutura de Pastas
less
Copiar
Editar

src/
├── main/
│   ├── java/
│   │   └── com.exemplo.caixa/
│   │       ├── MainApp.java              // Inicializador JavaFX
│   │       ├── controller/
│   │       ├── view/
│   │       ├── model/                    // DTOs/Entidades
│   │       ├── service/                  // Regras de negócio
│   │       └── repository/               // Interfaces JPA
│   ├── resources/
│   │   ├── application.properties
│   │   └── fxml/                         // Arquivos de layout

💾 4. Banco de Dados
Tabelas básicas:

produto (id, nome, preco, estoque)

venda (id, data, total)

item_venda (id, id_venda, id_produto, quantidade, subtotal)

usuario (id, nome, senha_hash, cargo)

🖥️ 5. Interface com JavaFX
Telas mínimas:

Tela de login (com autenticação)

Tela principal do caixa:

Scanner/campo de busca do produto

Lista de itens adicionados

Total acumulado

Botão de finalizar venda

Tela de histórico de vendas

Tela de cadastro/edição de produtos

Recomendações:

Use FXML + SceneBuilder para agilidade

Componentes úteis: TableView, ComboBox, TextField, Button, Label

Navegação com FXMLLoader.load(getClass().getResource(...))

🧠 6. Backend Spring Boot (Camada de Lógica)
Exemplo de entidade Produto:

java
Copiar
Editar
@Entity
public class Produto {
@Id @GeneratedValue
private Long id;
private String nome;
private BigDecimal preco;
private Integer estoque;
}

Exemplo de repositório:

java
Copiar
Editar
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
Optional<Produto> findByNome(String nome);
}

Exemplo de serviço:

java
Copiar
Editar
@Service
public class ProdutoService {
@Autowired private ProdutoRepository repo;

    public Produto buscarPorNome(String nome) {
        return repo.findByNome(nome).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void reduzirEstoque(Long id, int qtd) {
        Produto p = repo.findById(id).get();
        if (p.getEstoque() < qtd) throw new RuntimeException("Estoque insuficiente");
        p.setEstoque(p.getEstoque() - qtd);
        repo.save(p);
    }
}
🔌 7. Integração JavaFX + Spring Boot

Injeção manual de beans no JavaFX (sem Spring Boot Starter FX):

java
Copiar
Editar
public class MainApp extends Application {
private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(MySpringBootApp.class).run();
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }
}
✅ 8. Funcionalidades Prioritárias

Funcionalidade	Descrição

🛒 Adicionar item	Buscar produto por nome ou código, e adicionar à venda atual
🧾 Finalizar venda	Calcular total, salvar venda e itens da venda
📦 Controle de estoque	Atualizar estoque após venda, impedir venda com estoque zerado
🕓 Histórico de vendas	Visualizar vendas anteriores com filtro por data
👨‍💼 Login de usuário	Login com validação de credenciais
🛠️ CRUD de produtos	Cadastrar, editar e excluir produtos

📦 9. Empacotamento

Use o JLink ou jpackage para gerar instaladores nativos.

Crie um .jar executável com todos os módulos (fat jar ou uber jar).

📄 10. Extras (para produção)
Suporte a impressora fiscal (ECF/NFC-e via DLL ou WebService)

Geração de relatórios (JasperReports)

Permissões por tipo de usuário (ex: Admin vs Caixa)

Backup automático da base

Integração com leitor de código de barras