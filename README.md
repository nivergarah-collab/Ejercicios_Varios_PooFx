# Solución de referencia · Cartelera Cine Horizonte

Solución de referencia del ejercicio **ej09-peliculas**: la aplicación de escritorio con **JavaFX** y archivos **JSON** construida sobre el modelo entregado en la base. El caso completo está en [`ENUNCIADO.md`](ENUNCIADO.md).

> **Aviso importante**
>
> Este material es de práctica, creado con ayuda de inteligencia artificial. No reemplaza ninguna pauta oficial, y los resultados que obtengas aquí no aseguran el mismo resultado en una evaluación real. Intenta resolver primero el ejercicio en la rama `ej09-peliculas_practice` y usa esta rama solo para comparar.

## Ramas de este ejercicio

| Rama | Para qué sirve |
|---|---|
| [`ej09-peliculas_base`](../../tree/ej09-peliculas_base) | Proyecto base con el modelo y el enunciado. |
| [`ej09-peliculas_practice`](../../tree/ej09-peliculas_practice) | Copia de la base para practicar y subir tus avances. |
| [`ej09-peliculas_solucion`](../../tree/ej09-peliculas_solucion) | Esta rama: la solución de referencia. |

Índice de todos los ejercicios: rama [`main`](../../tree/main).

## Cómo ejecutar

1. Abre la carpeta en IntelliJ IDEA como proyecto Maven, con JDK **21**.
2. Ejecuta la clase `Launcher`, o desde una terminal: `mvn javafx:run`.

Al registrar datos, la aplicación crea en la carpeta raíz del proyecto los archivos `funciones_regulares.json` y `funciones_populares.json`. No se versionan (están en `.gitignore`); si no existen, la aplicación parte con la tabla vacía.

## Qué agrega esta solución sobre la base

```
pom.xml                                      Proyecto Maven (Java 21, JavaFX y Gson).
src/main/java/module-info.java               Módulo de la aplicación (requires, opens y exports).
src/main/java/cl/duoc/peliculas/             Launcher.java y App.java (arranque, sin cambios respecto de la base).
src/main/java/cl/duoc/peliculas/model/       Modelo entregado: Funcion.java, FuncionPopular.java, FuncionRegular.java, Reembolsable.java.
src/main/java/cl/duoc/peliculas/controller/  Controlador de la vista: MainController.java.
src/main/java/cl/duoc/peliculas/repository/  Persistencia: FuncionDao.java, FuncionRepository.java, GsonFuncionDao.java, PersistenciaException.java.
src/main/resources/cl/duoc/peliculas/view/   main-view.fxml (la única ventana).
```

- **Vista:** un solo archivo FXML con el formulario, la tabla, los botones Registrar y Eliminar, y el área de mensajes.
- **Controlador:** valida el formulario, habilita solo los campos del tipo elegido, muestra los mensajes en la ventana y pide las operaciones al Repository.
- **Persistencia:** una interfaz DAO, su implementación con Gson (un archivo por subtipo) y un Repository que mantiene los datos en memoria y deshace el cambio si falla el guardado.
- **Módulos:** usa `module-info.java`. La base no lo trae, porque agregarlo es parte del ejercicio.

```java
module cl.duoc.peliculas {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.peliculas.model to com.google.gson, javafx.base;
    opens cl.duoc.peliculas.controller to javafx.fxml;

    exports cl.duoc.peliculas;
}
```

Para comparar tu trabajo con esta solución: `git diff ej09-peliculas_practice ej09-peliculas_solucion`.
