<div align="right" size="1px">
<a href='https://github.com/vanskarner/CleanExampleKT/blob/master/README_EN.md'>English</a>
</div>
<br>

Aplicación simple Android en Kotlin que sigue las mejores prácticas de **Martin, Robert C.**, en su libro **Clean Architecture: A Craftsman's Guide to Software Structure and Design** (2017).

Este ejemplo simplificado se puede considerar una extensión del repositorio más completo y documentado, donde se habla a fondo de las buenas prácticas que hay detrás de la 
arquitectura de software y se puede encontrar en [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki)

<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/7d7c53a6-7c85-4456-a725-99814d3b1eb5" alt="CleanArchitectureCover_2017" style="display: block; margin: auto; width: 400px;">
</p>

## ¿Qué hay de nuevo en este repositorio?

No mucho, la teoria sigue siendo la misma que se puede encontrar en [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki). 
La novedad es que se usa Kotlin, Corrutinas, ViewModel, LiveData y Hilt.

## ¿Por qué has separado esto en módulos? ¿Acaso todos los proyectos deben ser multimódulo?

La separación en módulos obedece a cumplir con el enfoque arquitectónico predentendido, en este caso se esta siguiendo el enfoque [Paquete por Componente](https://github.com/vanskarner/CleanMovie/wiki/The-Code-Decoupling#package-by-component). 

No necesariamente todos los proyectos deben ser multimódulo, se puede literalmente copiar todos las clases e interfaces del componente user y ponerlo dentro del modulo app en un 
paquete llamado por ejemplo "com.vanskarner.cleanexamplekt.features.user" y actualizar lo restante con el mínimo esfuerzo, sin embargo el problema es que se puede saltar niveles 
como se explica [aqui](https://github.com/vanskarner/CleanMovie/wiki/Desacoplamiento-de-C%C3%B3digo#la-disciplina-no-es-suficiente).

## En la wiki de CleanMovie se indica que las estructuras de datos no deben tener métodos que conviertan de un formato a otro. Sin embargo, aquí hay extensiones que realizan esa tarea. ¿Existe una contradicción?

![image](https://github.com/vanskarner/CleanExampleKT/assets/39975255/160c79c9-2f62-46b3-90bc-b1d061c02a98)


No, en absoluto. Aquí es importante tener en cuenta el contexto. Kotlin, con sus extensiones, ofrece la ventaja de la herencia sin sus desventajas. 
Por lo tanto, no hay contradicción, y es totalmente válido elegir esta forma de mapear, al igual que también sería válido crear una clase dedicada para esa tarea. 
Ambas opciones son válidas.

## ¿Por qué no se incluyen diagramas y gráficos de las métricas?

Como dije antes, este es un ejemplo simplificado. Si has leído y comprendido la wiki de [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki), no deberías tener problemas para hacerlo tú mismo. 
En cuanto a las métricas, no he encontrado un software que contenga todas las métricas que nos proporciona JArchitect 😔.

## ¿Se incluirán más ejemplos, como por ejemplo, utilizando DataBinding, Compose y Navigation?

Posiblemente, más adelante, incluiré más ramas utilizando características o detalles adicionales. También, ustedes pueden hacerlo y sugerirlo [aquí](https://github.com/vanskarner/CleanExampleKT/discussions/categories/ideas).

## ¿Incluye pruebas?

| User  | ViewModel |
| --- | --- |
|  ![user](https://github.com/vanskarner/CleanExampleKT/assets/39975255/357da6aa-19c1-4749-bf64-06807a8c92ed) | ![UI_ViewModel](https://github.com/vanskarner/CleanExampleKT/assets/39975255/9ff51fd2-d315-4d7d-8e1b-f6775455015a) |

| UI Android |
| --- |
| ![UI_Android](https://github.com/vanskarner/CleanExampleKT/assets/39975255/e4449e2d-36cd-4c3f-a3dc-d1d108b949ad) |

Por supuesto, toda buena arquitectura tiene pruebas. En cada componente se encuentran las pruebas unitarias y de instrumentación necesarias.

## ¿Hay algo más que deba saber o que me esté perdiendo?

Sí, fíjate en cómo está construida cada clase, qué modificador de acceso se utiliza en cada clase e interfaz, así como en cómo están dispuestos los paquetes; todo tiene un propósito.

## ¿Quién eres?

El mismo que ha escrito la wiki y el código de [CleanMovie](https://github.com/vanskarner/CleanMovie) 😁.

## Discusiones

Consulte la sección de discusiones [AQUÍ](https://github.com/vanskarner/CleanExampleKT/discussions).

