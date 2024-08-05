
---

# Maximal Rectangle in a Binary Matrix

## Descrição da Solução

Para resolver o problema de encontrar o maior retângulo contendo apenas '1' em uma matriz binária 2D, usamos uma abordagem que envolve a aplicação de um algoritmo de "histograma" em cada linha da matriz. A solução pode ser descrita em passos:

1. **Transformação da Matriz:**
    - Imagine cada linha da matriz como a base de um histograma.
    - Para cada célula '1' na matriz, conte quantas células '1' consecutivas existem na coluna atual até a linha atual.

2. **Construção do Histograma:**
    - Percorremos cada linha da matriz.
    - Para cada célula '1', incrementamos a contagem de alturas de '1' na mesma coluna.
    - Para cada célula '0', a altura na mesma coluna é zerada.

3. **Cálculo da Maior Área:**
    - Para cada linha da matriz, usamos a altura das colunas como entrada para um problema de encontrar a maior área de retângulo em um histograma.
    - Utilizamos uma pilha para calcular a área máxima possível do retângulo para cada linha tratada como um histograma.

## Explicação do Código em Java

O código a seguir implementa o algoritmo descrito acima em Java:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class RectangleMatrix {
    private final char[][] matrix;

    public RectangleMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public int maximalRectangle() {
        if (matrix.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (chars[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int[] h = new int[heights.length + 1];
        System.arraycopy(heights, 0, h, 0, heights.length);

        for (int i = 0; i < h.length; i++) {
            while (!stack.isEmpty() && h[i] < h[stack.peek()]) {
                int height = h[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        RectangleMatrix rectangleMatrix = new RectangleMatrix(matrix);
        System.out.println(rectangleMatrix.maximalRectangle());
    }
}
```

### Descrição do Código:

1. **Classe `RectangleMatrix`:**
    - Contém um construtor que recebe uma matriz binária `matrix`.
    - Método `maximalRectangle`:
        - Inicializa a área máxima (`maxArea`) como 0.
        - Cria um array `heights` para armazenar as alturas dos histogramas, do mesmo tamanho que o número de colunas da matriz.
        - Para cada linha da matriz, atualiza as alturas:
            - Se a célula atual for '1', incrementa a altura correspondente.
            - Se a célula atual for '0', zera a altura correspondente.
        - Calcula a maior área de retângulo possível na linha atual, tratada como um histograma, usando o método `largestRectangleArea`.
    - Método `largestRectangleArea`:
        - Usa uma pilha (`Deque`) para armazenar os índices das alturas.
        - Adiciona um valor sentinel (`0`) ao final do array de alturas para garantir que todas as barras do histograma sejam processadas.
        - Para cada índice, verifica se a altura atual é menor do que a altura na pilha:
            - Se for, calcula a área do retângulo com a altura da barra na pilha e a largura até o índice atual.
            - Atualiza a área máxima (`maxArea`) se a nova área for maior.
        - Retorna a maior área encontrada.
    - Método `main`:
        - Cria uma matriz de exemplo.
        - Instancia a classe `RectangleMatrix` com a matriz.
        - Chama o método `maximalRectangle` e imprime o resultado.

## Fluxograma

Abaixo está uma descrição textual que pode ser utilizada para criar um fluxograma visual:

1. **Início**
    - Start

2. **Inicialização**
    - Inicialize `maxArea` como 0
    - Inicialize `heights` como uma lista de zeros do comprimento das colunas

3. **Iteração pelas Linhas da Matriz**
    - Para cada linha da matriz:
        - Para cada célula na linha:
            - Se célula == '1': `heights[j]++`
            - Se célula == '0': `heights[j] = 0`
        - `maxArea = Math.max(maxArea, largestRectangleArea(heights))`

4. **Atualização das Alturas**
    - Para cada célula na linha:
        - Se célula == '1': `heights[j]++`
        - Se célula == '0': `heights[j] = 0`

5. **Cálculo da Maior Área do Histograma**
    - Utilize uma pilha para calcular a maior área de retângulo no histograma atual
    - Atualize `maxArea` se a área calculada for maior

6. **Retorno da Maior Área**
    - Retorne `maxArea`

7. **Fim**
    - End

Você pode usar ferramentas como Lucidchart, Draw.io ou até mesmo diagramas de fluxo no PowerPoint para desenhar os passos descritos acima.

---