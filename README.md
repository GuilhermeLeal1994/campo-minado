# Campo Minado

Projeto de uma versão do jogo **Campo Minado em Java**, com interface gráfica desenvolvida em **Swing** e arquitetura **MVC (Model-View-Controller)**.

O projeto foi desenvolvido de forma incremental, com foco em organização de código, separação de responsabilidades, testes, interação gráfica e expansão das funcionalidades do jogo.

## Estrutura do projeto

```text
src/
├── main/
│   ├── JogoCampoMinadoGUI.java
│   └── JogoCampoMinado.java
├── controller/
│   ├── CampoMinadoController.java
│   └── AcoesJogador.java
├── view/
│   └── CampoMinadoView.java
├── model/
│   ├── Tabuleiro.java
│   ├── Celula.java
│   ├── LeituraTabuleiro.java
│   └── Recordes.java
└── test/
    └── CampoMinadoTest.java
```

## Funcionalidades

### Sistema de vidas por dificuldade

- **Iniciante:** 9x9, 10 minas, 2 vidas.
- **Intermediário:** 16x16, 40 minas, 3 vidas.
- **Avançado:** 16x30, 99 minas, 5 vidas.
- Ao revelar uma mina, o jogador perde uma vida.
- A partida termina quando todas as vidas são consumidas.

### Sistema de revelação

- Revelação individual de células.
- Revelação automática em cascata.
- Contagem de minas vizinhas.
- Proteção contra revelação de células já abertas.
- Proteção contra revelação de células marcadas.
- Cliques repetidos em células já reveladas não são contabilizados como novas jogadas.

### Sistema de bandeiras

- Marcação e desmarcação com o botão direito.
- Limite de bandeiras baseado na quantidade total de minas.
- Não é possível adicionar novas bandeiras quando o limite é atingido.
- Bandeiras existentes podem ser removidas mesmo com zero minas restantes.
- O contador de minas restantes nunca fica negativo.

### Estatísticas em tempo real

- Tempo decorrido.
- Tempo limite.
- Minas restantes.
- Células reveladas.
- Número de jogadas.
- Vidas restantes.
- Atualização das informações durante a partida.

### Seleção de dificuldade

- Iniciante.
- Intermediário.
- Avançado.

### Cronômetro e limite de tempo

O jogador pode escolher:

- Sem limite.
- 30 segundos.
- 60 segundos.
- 120 segundos.
- 300 segundos.

Ao atingir o limite de tempo, a partida é encerrada em derrota.

### Sistema de dicas

- Botão **Dica** disponível durante a partida.
- A dica destaca uma célula segura.
- Limite de até **3 dicas por partida**.
- Mensagens informam o jogador sobre a dica utilizada.

### Sistema de recordes — Top 5

O jogo possui um sistema de recordes separado por dificuldade.

- Top 5 de **Iniciante**.
- Top 5 de **Intermediário**.
- Top 5 de **Avançado**.
- Os recordes armazenam **nome do jogador e tempo em segundos**.
- Ao vencer uma partida, o tempo é verificado para determinar se o jogador pode entrar no Top 5.
- Quando entra no Top 5, o jogador pode informar seu nome.
- Caso o nome não seja informado, é utilizado o nome padrão `Jogador`.
- Os recordes são persistidos localmente.
- O botão **🏆 Ver Top 5** permite visualizar os cinco melhores tempos de cada dificuldade em uma janela separada.
- A visualização apresenta as três dificuldades em colunas independentes.

## Interface gráfica

### Temas visuais

- **Cyberpunk Neon**.
- **Terminal Retro**.
- Variantes claras dos temas.
- Detecção inicial da aparência clara/escura do sistema para definir o tema de fundo inicial.

### Temas do tabuleiro

- Tabuleiro Padrão.
- Tabuleiro Claro.
- Tabuleiro Escuro.

### Adaptação da interface

- Redimensionamento proporcional dos componentes.
- Tabuleiro ajustado ao espaço disponível.
- Remoção de barras de rolagem indesejadas.
- Grade adaptada à dificuldade.
- Correção do mapeamento entre posição visual e coordenadas do Model.
- Interface ajustada para diferentes tamanhos de tabuleiro.

### Modo tela cheia

- Botão visível de **Tela cheia**.
- Atalho **F11** para alternar entre tela cheia e modo normal.

### Tutorial integrado

A interface possui um tutorial com instruções sobre:

- Revelação de células.
- Uso de bandeiras.
- Sistema de vidas.
- Dicas.
- Condições de vitória e derrota.
- Limite de tempo.
- Modo tela cheia.

## Arquitetura MVC

### Model

`Tabuleiro`, `Celula`, `LeituraTabuleiro` e `Recordes`.

Responsável pelas regras, estado do jogo, células, leitura do tabuleiro e armazenamento/gerenciamento dos recordes.

### View

`CampoMinadoView`.

Responsável pela interface gráfica, tutorial, estatísticas, temas, redimensionamento, tela cheia, solicitação do nome do jogador e visualização do Top 5.

### Controller

`CampoMinadoController` e `AcoesJogador`.

Responsável pela comunicação entre View e Model, ações do jogador, configuração da dificuldade, estatísticas, cronômetro, dicas, finalização da partida e gerenciamento dos recordes.

## Evolução do projeto

### Etapa 1 — Sistema de vidas

- Inclusão de vidas no `Tabuleiro`.
- Definição das vidas conforme a dificuldade.
- Perda de vida ao atingir uma mina.
- Derrota somente quando as vidas chegam a zero.
- Exibição das vidas na interface.
- Integração entre Model, Controller e View.

### Etapa 2 — Interface e interação

- Temas visuais e variantes claras/escuro.
- Detecção inicial da aparência do sistema.
- Limite estrito de bandeiras.
- Remoção de bandeiras com contador em zero.
- Contador de minas sem valores negativos.
- Redimensionamento do tabuleiro e ajuste proporcional.
- Modo tela cheia, botão de tela cheia e atalho F11.
- Correção do mapeamento das células.
- Cliques em células já reveladas não aumentam o contador de jogadas.
- Atualização das estatísticas durante a partida.
- Sistema de dicas com limite de três utilizações por partida.

### Etapa 3 — Sistema de recordes e Top 5

- Criação da classe `Recordes`.
- Armazenamento dos cinco melhores tempos por dificuldade.
- Registro do nome do jogador junto ao tempo.
- Verificação de entrada no Top 5 ao vencer.
- Solicitação do nome do jogador para novos recordes.
- Persistência local dos recordes.
- Botão **🏆 Ver Top 5** na tela inicial.
- Janela dedicada para visualização dos recordes.
- Separação dos recordes por dificuldade.
- Ordenação dos melhores tempos.
- Manutenção dos cinco melhores resultados por dificuldade.

### Etapa 4 — Modo Customizado

- Inclusão do **Modo Customizado** na tela inicial.
- Criação de uma janela de configuração para o jogador definir:
  - Largura do tabuleiro (colunas).
  - Altura do tabuleiro (linhas).
  - Quantidade de minas.
- Validação dos valores informados antes de iniciar a partida.
- Campos vazios ou com valores inválidos não são aceitos.
- O Modo Customizado exige tabuleiros com no mínimo **5x5**.
- A quantidade de minas deve ser maior que zero.
- Não é permitido utilizar uma quantidade de minas maior ou igual ao número total de células.
- Validação contra valores numéricos excessivamente grandes e possíveis overflows no cálculo do total de células.
- Cancelamento da janela de configuração não inicia uma nova partida.
- Integração da configuração personalizada com o Controller através da mesma comunicação utilizada pelas dificuldades tradicionais.
- O `Tabuleiro` passa a trabalhar de forma segura com dimensões e quantidade de minas dinâmicas.
- O sistema mantém as regras das dificuldades predefinidas:
  - Iniciante: 9x9, 10 minas, 2 vidas.
  - Intermediário: 16x16, 40 minas, 3 vidas.
  - Avançado: 16x30, 99 minas, 5 vidas.
- Partidas customizadas utilizam **2 vidas**, sem alterar as configurações das dificuldades oficiais.
- Partidas customizadas não são associadas indevidamente aos recordes de Iniciante, Intermediário ou Avançado.
- O redimensionamento dinâmico da interface foi mantido compatível com os novos tamanhos de tabuleiro.
- A implementação mantém a separação de responsabilidades da arquitetura MVC:
  - **View:** coleta e valida os dados da customização.
  - **Controller:** recebe a configuração e inicia a partida.
  - **Model:** cria e mantém o estado do tabuleiro personalizado.

## Compilação

A partir da pasta raiz do projeto:

```powershell
javac -d bin src\main\*.java src\controller\*.java src\view\*.java src\model\*.java
```

## Execução

### Interface gráfica

```text
java -cp bin main.JogoCampoMinadoGUI
```

### Versão em console

```text
java -cp bin main.JogoCampoMinado
```

## Testes

Os testes unitários estão localizados em:

```text
src/test/CampoMinadoTest.java
```

A execução depende da configuração do **JUnit** no ambiente de desenvolvimento.

## Tecnologias

- Java
- Java Swing
- JUnit
- Arquitetura MVC
- Git / GitHub

## Observações

- A interface gráfica utiliza Swing.
- As regras do jogo permanecem concentradas no Model.
- O Controller coordena as ações e a comunicação entre as camadas.
- A View é responsável pela apresentação e interação visual.
- As alterações visuais não modificam diretamente as regras do jogo.
- Os recordes são gerenciados pela classe `Recordes` e utilizados pelo Controller e pela View.
- Antes de executar, compile os arquivos direcionando os `.class` para a pasta `bin`.
