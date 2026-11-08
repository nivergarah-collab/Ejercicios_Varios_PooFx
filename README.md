# Solución de referencia · Cuentas del Departamento Los Aromos

Solución de referencia del ejercicio **ej06-gastos**: la aplicación de escritorio con **JavaFX** y archivos **JSON** construida sobre el modelo entregado en la base. El caso completo está en [`ENUNCIADO.md`](ENUNCIADO.md).

> **Aviso importante**
>
> Este material es de práctica, creado con ayuda de inteligencia artificial. No reemplaza ninguna pauta oficial, y los resultados que obtengas aquí no aseguran el mismo resultado en una evaluación real. Intenta resolver primero el ejercicio en la rama `ej06-gastos_practice` y usa esta rama solo para comparar.

## Ramas de este ejercicio

| Rama | Para qué sirve |
|---|---|
| [`ej06-gastos_base`](../../tree/ej06-gastos_base) | Proyecto base con el modelo y el enunciado. |
| [`ej06-gastos_practice`](../../tree/ej06-gastos_practice) | Copia de la base para practicar y subir tus avances. |
| [`ej06-gastos_solucion`](../../tree/ej06-gastos_solucion) | Esta rama: la solución de referencia. |

Índice de todos los ejercicios: rama [`main`](../../tree/main).

## Cómo ejecutar

1. Abre la carpeta en IntelliJ IDEA como proyecto Maven, con JDK **21**.
2. Ejecuta la clase `Launcher`, o desde una terminal: `mvn javafx:run`.

Al registrar datos, la aplicación crea en la carpeta raíz del proyecto los archivos `salidas.json` y `servicios.json`. No se versionan (están en `.gitignore`); si no existen, la aplicación parte con la tabla vacía.

## Qué agrega esta solución sobre la base

```
pom.xml                                   Proyecto Maven (Java 21, JavaFX y Gson).
src/main/java/module-info.java            Módulo de la aplicación (requires, opens y exports).
src/main/java/cl/duoc/gastos/             Launcher.java y App.java (arranque, sin cambios respecto de la base).
src/main/java/cl/duoc/gastos/model/       Modelo entregado: Gasto.java, GastoSalida.java, GastoServicio.java, Reembolsable.java.
src/main/java/cl/duoc/gastos/controller/  Controlador de la vista: MainController.java.
src/main/java/cl/duoc/gastos/repository/  Persistencia: GastoDao.java, GastoRepository.java, GsonGastoDao.java, PersistenciaException.java.
src/main/resources/cl/duoc/gastos/view/   main-view.fxml (la única ventana).
```

- **Vista:** un solo archivo FXML con el formulario, la tabla, los botones Registrar y Eliminar, y el área de mensajes.
- **Controlador:** valida el formulario, habilita solo los campos del tipo elegido, muestra los mensajes en la ventana y pide las operaciones al Repository.
- **Persistencia:** una interfaz DAO, su implementación con Gson (un archivo por subtipo) y un Repository que mantiene los datos en memoria y deshace el cambio si falla el guardado.
- **Módulos:** usa `module-info.java`. La base no lo trae, porque agregarlo es parte del ejercicio.

```java
module cl.duoc.gastos {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens cl.duoc.gastos.model to com.google.gson, javafx.base;
    opens cl.duoc.gastos.controller to javafx.fxml;

    exports cl.duoc.gastos;
}
```

Para comparar tu trabajo con esta solución: `git diff ej06-gastos_practice ej06-gastos_solucion`.
