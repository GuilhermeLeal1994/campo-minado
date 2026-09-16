# Campo Minado
Projeto de uma versão do jogo Campo Minado em Java, com interface gráfica desenvolvida em Swing e arquitetura MVC (Model-View-Controller).

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
│   └── LeituraTabuleiro.java
└── test/
    └── CampoMinadoTest.java
```

## Funcionalidades

**Sistema de vidas por dificuldade:**
• Iniciante: 9x9, 10 minas, 2 vidas.
• Intermediário: 16x16, 40 minas, 3 vidas.
• Avançado: 16x30, 99 minas, 5 vidas.
• Ao revelar uma mina, o jogador perde uma vida.
• A partida termina quando todas as vidas são consumidas.

**Sistema de revelação:**
• Revelação individual de células.
• Revelação automática em cascata.
• Contagem de minas vizinhas.
• Proteção contra revelação de células já abertas.
• Proteção contra revelação de células marcadas.

**Sistema de bandeiras:**
• Marcação e desmarcação com o botão direito.
• Limite de bandeiras baseado na quantidade de minas.
• Não é possível adicionar novas bandeiras quando o limite é atingido.
• Bandeiras existentes podem ser removidas mesmo com zero minas restantes.
• O contador de minas restantes nunca fica negativo.

**Estatísticas em tempo real:**
• Tempo decorrido.
• Tempo limite.
• Minas restantes.
• Células reveladas.
• Número de jogadas.
• Vidas restantes.
• Clicar novamente em uma célula já revelada não contabiliza uma nova jogada.

**Seleção de dificuldade:** Iniciante, Intermediário e Avançado.

**Cronômetro e limite de tempo:** Sem limite, 30, 60, 120 ou 300 segundos, com derrota automática ao esgotar o tempo.

##Interface gráfica

**Temas visuais:**
• Cyberpunk Neon.
• Terminal Retro.
• Variantes claras dos temas.

**Adaptação da interface:**
• Redimensionamento proporcional dos componentes.
• Tabuleiro ajustado ao espaço disponível.
• Remoção de barras de rolagem indesejadas.
• Grade adaptada à dificuldade.
• Correção do mapeamento entre posição visual e coordenadas do Model.

**Modo tela cheia:** botão visível e atalho F11.

Tutorial integrado com instruções sobre revelação, bandeiras, vidas e condições de vitória e derrota.

## Arquitetura MVC

Model — Tabuleiro, Celula e LeituraTabuleiro. Responsável pelas regras e pelo estado do jogo.

View — CampoMinadoView. Responsável pela interface gráfica, tutorial, estatísticas, temas, redimensionamento e tela cheia.

Controller — CampoMinadoController e AcoesJogador. Responsável pela comunicação entre View e Model, ações do jogador, dificuldade, estatísticas, cronômetro e estado da partida.

## Evolução do projeto

Etapa 1 — Sistema de vidas:
• Inclusão de vidas no Tabuleiro.
• Definição das vidas conforme a dificuldade.
• Perda de vida ao atingir uma mina.
• Derrota somente quando as vidas chegam a zero.
• Exibição das vidas na interface.
• Integração entre Model, Controller e View.

Etapa 2 — Interface e interação:
• Temas visuais e variantes claras/escuro.
• Detecção inicial da aparência do sistema.
• Limite estrito de bandeiras.
• Remoção de bandeiras com contador em zero.
• Contador de minas sem valores negativos.
• Redimensionamento do tabuleiro e ajuste proporcional.
• Modo tela cheia, botão de tela cheia e atalho F11.
• Correção do mapeamento das células.
• Cliques em células já reveladas não aumentam o contador de jogadas.
• Atualização das estatísticas durante a partida.

## Compilação

A partir da pasta raiz do projeto:

```powershell
javac -d bin src\main\*.java src\controller\*.java src\view\*.java src\model\*.java
```

## Execução

Interface gráfica:
```
java -cp bin main.JogoCampoMinadoGUI
```
Versão em console:
```
java -cp bin main.JogoCampoMinado
```

## Testes

Os testes unitários estão localizados em src/test/CampoMinadoTest.java. A execução depende da configuração do JUnit no ambiente de desenvolvimento.

## Tecnologias

• Java
• Java Swing
• JUnit
• Arquitetura MVC
• Git / GitHub

## Observações

• A interface gráfica utiliza Swing.
• As regras do jogo permanecem concentradas no Model.
• O Controller coordena as ações e a comunicação entre as camadas.
• A View é responsável pela apresentação e interação visual.
• As alterações visuais não modificam diretamente as regras do jogo.
• Antes de executar, compile os arquivos direcionando os .class para a pasta bin.