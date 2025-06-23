# box-flow

Banco de Dados
Tabelas básicas:

produto (id, nome, preco, estoque)

venda (id, data, total)

item_venda (id, id_venda, id_produto, quantidade, subtotal)

usuario (id, nome, senha_hash, cargo)

Funcionalidade	Descrição

🛒 Adicionar item	Buscar produto por nome ou código, e adicionar à venda atual
🧾 Finalizar venda	Calcular total, salvar venda e itens da venda
📦 Controle de estoque	Atualizar estoque após venda, impedir venda com estoque zerado
🕓 Histórico de vendas	Visualizar vendas anteriores com filtro por data
👨‍💼 Login de usuário	Login com validação de credenciais
🛠️ CRUD de produtos	Cadastrar, editar e excluir produtos

📦 Empacotamento

Use o JLink ou jpackage para gerar instaladores nativos.

Crie um .jar executável com todos os módulos (fat jar ou uber jar).
