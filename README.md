# Campo Minado

Este projeto é uma versão em Java do jogo Campo Minado com interface gráfica Swing e arquitetura MVC, desenvolvido com foco em boas práticas, modularidade e expansibilidade.

## Estrutura do projeto

- `src/main` - classes principais de execução.
  - `JogoCampoMinadoGUI.java` - entrada do jogo em modo gráfico.
  - `JogoCampoMinado.java` - alternativa de execução em console.
- `src/controller` - controlador MVC.
  - `CampoMinadoController.java` - lógica de jogo e sincronização entre View e Model.
  - `AcoesJogador.java` - interface de ações disparadas pela View.
- `src/view` - camada de interface gráfica.
  - `CampoMinadoView.java` - tela do jogo, tutorial, temas visuais, contadores e estatísticas.
- `src/model` - modelo de domínio do jogo.
  - `Tabuleiro.java` - lógica do tabuleiro, minas, cálculo dinâmico de vidas, revelação e vitória.
  - `Celula.java` - estado de cada célula do tabuleiro.
  - `LeituraTabuleiro.java` - interface de leitura do estado do tabuleiro.
- `src/test` - testes unitários.
  - `CampoMinadoTest.java`

## Funcionalidades

- **Sistema de Vidas Dinâmico por Dificuldade:** O erro em minas consome vidas antes de encerrar o jogo, escalonando conforme o desafio:
  - *Iniciante (9x9 - 10 minas):* 2 vidas
  - *Intermediário (16x16 - 40 minas):* 3 vidas
  - *Avançado (16x30 - 99 minas):* 5 vidas
- Escolha de dificuldade: Iniciante, Intermediário e Avançado.
- Estatísticas atualizadas em tempo real: tempo, minas restantes, células reveladas, jogadas e vidas restantes.
- Temas visuais avançados na View (Cyberpunk Neon, Terminal Retro e variantes de modo claro/escuro), mantendo a integridade da arquitetura MVC.
- Tutorial integrado com instruções de jogo.
- Tempo limite selecionável (até 5 minutos) com cronômetro regressivo e derrota automática se estourar o prazo.
- Arquitetura MVC limpa e rigorosamente organizada em pacotes.

## Compilação

Execute no terminal a partir da pasta do projeto:

```powershell
javac src\main\*.java src\controller\*.java src\view\*.java src\model\*.java
```

## Execução

Para iniciar a interface gráfica:

```powershell
java -cp bin main.JogoCampoMinadoGUI
```

Para executar a versão em console:

```powershell
java -cp bin main.JogoCampoMinado
```

## Testes

Se você tiver o JUnit configurado, execute os testes em `src/test/CampoMinadoTest.java` com seu ambiente de testes Java.

## Observações

- A interface gráfica usa Swing e respeita componentes personalizados e temas visuais adaptativos.
- Certifique-se de compilar todos os arquivos direcionando para a pasta bin (-d bin) antes de executar.