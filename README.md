# dibujo

Dibujo é uma versão em linha de comando (CLI) de um programa de desenho.

## Comandos

O usuário pode realiar os seguintes comandos:

- `C w h`, que cria um canvas de `w` posições por `h` posições, onde `w` é _width_ (largura) e `h` é `height` (altura).
- `L xS yS xE yE`, que cria uma linha iniciando na posição (xS, yS) e terminando na posição (xE, yE), onde `S` é _Start_ (início) e `E` é _End_ (fim). A linha é representada no canvas com o caracter `x`. Só há suporte a linhas horizontais, em que `yS` é igual a `yE`, e linhas verticais, em que `xS` é igual a `yE`.
- `R xULC yULC xLRC yLRC`, que cria um retângulo iniciando na posição (xULC, yULC) e terminando na posição (xLRC, yLRC), onde `ULC` é _Upper Left Corner_ (canto superior esquerdo) e `LRC` é _Lower Right Corner_ (canto inferior direito). O retângulo é "oco", não contendo nenhum caracter dentro, e seus limites são representados no canvas com o caracter `x`.
- `B x y c`, que preenche todas as posições adjacentes à posição (x, y) com a "cor" `c`, até que haja alguma barreira como: uma linha, um retângulo ou o fim do canvas.
- `Q`, que finaliza o programa.

É impresso `enter command:` ao iniciar o programa e também depois do resultado de cada comando.

## Exemplo de uso

Crie um canvas de 20 de largura por 4 de altura:

```
enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
```

Defina uma linha horizontal com 6 posições a partir da 2ª linha do canto esquerdo:

```
enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------
```

Defina uma linha vertical com 2 posições a partir da 6ª coluna e 3ª linha:

```
enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------
```

Defina um retângulo cujo canto superior esquerdo é a posição que está na 14ª coluna e 1ª linha e cujo canto inferior direito é a 18ª coluna e 3ª linha:

```
enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------
```

Preencha o canvas com a "cor" `o` a partir da 10ª coluna e 3ª linha:

```
enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------
```

Saia do programa:

```
enter command: Q
```