# Ejercicios varios · POO con JavaFX

Ejercicios de práctica de programación orientada a objetos con JavaFX, Maven y JSON (Gson). Material creado por estudiantes con apoyo de IA como práctica personal. **No es material oficial** y es solo referencial.

## Cómo está organizado

La rama `main` solo contiene este índice. **Cada ejercicio vive en sus propias ramas** y cada rama es un proyecto Maven completo en la raíz del repositorio (Java 21, JavaFX 21, Gson). Los nombres siguen el patrón `ejNN-tema_tipo`.

Hay dos "familias" de ramas, según desde dónde quieras empezar a practicar:

**Familia 1: desde cero (excepto el modelo)**

- `ejNN-tema_base`: punto de partida oficial. Trae el modelo ya hecho y el `ENUNCIADO.md`. Hay que construir todo lo demás: repository (DAO, excepción y Repository), vista, controlador, `module-info.java` y dependencias.
- `ejNN-tema_practice`: copia de la base para que subas tus propios avances sin tocar la base. Se parte igual que la base.

**Familia 2: v2, con la capa de datos ya resuelta**

- `ejNN-tema_base_v2`: la base más la carpeta `repository` de la solución (DAO, excepción y Repository). Quedan por hacer la vista (Scene Builder), el controlador, el `module-info.java` y las dependencias. Sirve para practicar solo JavaFX/controlador.
- `ejNN-tema_practice_v2`: copia de la base v2 donde se sube el avance propio. Es la rama que va cambiando.

**Referencia**

- `ejNN-tema_solucion`: una solución completa del ejercicio, con su propio README.

Reglas del juego:

- Las ramas `_base` y `_base_v2` **no cambian** y no llevan `module-info.java`; ese archivo se escribe como parte del ejercicio.
- El avance propio va **solo** en `_practice` y `_practice_v2`.
- Las soluciones se pueden actualizar cuando se mejora el enfoque.
- Una `_practice_v2` que sigue en el mismo commit que su `_base_v2` significa "aún no empezada".

Para descargar un ejercicio, cambia a su rama con el selector de ramas de GitHub y usa **Code > Download ZIP**. El ZIP trae solo ese proyecto.

## Ejercicios

| Ejercicio | Tema | Rama base | Rama solución | Rama práctica | Base v2 | Práctica v2 |
|---|---|---|---|---|---|---|
| ej01 | Clínica veterinaria | [`ej01-veterinaria_base`](../../tree/ej01-veterinaria_base) | [`ej01-veterinaria_solucion`](../../tree/ej01-veterinaria_solucion) | [`ej01-veterinaria_practice`](../../tree/ej01-veterinaria_practice) | [`ej01-veterinaria_base_v2`](../../tree/ej01-veterinaria_base_v2) | [`ej01-veterinaria_practice_v2`](../../tree/ej01-veterinaria_practice_v2) |
| ej02 | Biblioteca | [`ej02-biblioteca_base`](../../tree/ej02-biblioteca_base) | [`ej02-biblioteca_solucion`](../../tree/ej02-biblioteca_solucion) | [`ej02-biblioteca_practice`](../../tree/ej02-biblioteca_practice) | [`ej02-biblioteca_base_v2`](../../tree/ej02-biblioteca_base_v2) | [`ej02-biblioteca_practice_v2`](../../tree/ej02-biblioteca_practice_v2) |
| ej03 | Turnos | [`ej03-turnos_base`](../../tree/ej03-turnos_base) | [`ej03-turnos_solucion`](../../tree/ej03-turnos_solucion) | [`ej03-turnos_practice`](../../tree/ej03-turnos_practice) | [`ej03-turnos_base_v2`](../../tree/ej03-turnos_base_v2) | [`ej03-turnos_practice_v2`](../../tree/ej03-turnos_practice_v2) |
| ej04 | Inventario | [`ej04-inventario_base`](../../tree/ej04-inventario_base) | [`ej04-inventario_solucion`](../../tree/ej04-inventario_solucion) | [`ej04-inventario_practice`](../../tree/ej04-inventario_practice) | [`ej04-inventario_base_v2`](../../tree/ej04-inventario_base_v2) | [`ej04-inventario_practice_v2`](../../tree/ej04-inventario_practice_v2) |
| ej05 | Torneo | [`ej05-torneo_base`](../../tree/ej05-torneo_base) | [`ej05-torneo_solucion`](../../tree/ej05-torneo_solucion) | [`ej05-torneo_practice`](../../tree/ej05-torneo_practice) | [`ej05-torneo_base_v2`](../../tree/ej05-torneo_base_v2) | [`ej05-torneo_practice_v2`](../../tree/ej05-torneo_practice_v2) |
| ej06 | Gastos | [`ej06-gastos_base`](../../tree/ej06-gastos_base) | [`ej06-gastos_solucion`](../../tree/ej06-gastos_solucion) | [`ej06-gastos_practice`](../../tree/ej06-gastos_practice) | [`ej06-gastos_base_v2`](../../tree/ej06-gastos_base_v2) | [`ej06-gastos_practice_v2`](../../tree/ej06-gastos_practice_v2) |
| ej07 | Playlist | [`ej07-playlist_base`](../../tree/ej07-playlist_base) | [`ej07-playlist_solucion`](../../tree/ej07-playlist_solucion) | [`ej07-playlist_practice`](../../tree/ej07-playlist_practice) | [`ej07-playlist_base_v2`](../../tree/ej07-playlist_base_v2) | [`ej07-playlist_practice_v2`](../../tree/ej07-playlist_practice_v2) |
| ej08 | Agenda | [`ej08-agenda_base`](../../tree/ej08-agenda_base) | [`ej08-agenda_solucion`](../../tree/ej08-agenda_solucion) | [`ej08-agenda_practice`](../../tree/ej08-agenda_practice) | [`ej08-agenda_base_v2`](../../tree/ej08-agenda_base_v2) | [`ej08-agenda_practice_v2`](../../tree/ej08-agenda_practice_v2) |
| ej09 | Películas | [`ej09-peliculas_base`](../../tree/ej09-peliculas_base) | [`ej09-peliculas_solucion`](../../tree/ej09-peliculas_solucion) | [`ej09-peliculas_practice`](../../tree/ej09-peliculas_practice) | [`ej09-peliculas_base_v2`](../../tree/ej09-peliculas_base_v2) | [`ej09-peliculas_practice_v2`](../../tree/ej09-peliculas_practice_v2) |
| ej10 | Vivero (simulacro EP2 del curso, solución del profesor) | [`ej10-vivero_base`](../../tree/ej10-vivero_base) | [`ej10-vivero_solucion`](../../tree/ej10-vivero_solucion) | [`ej10-vivero_practice`](../../tree/ej10-vivero_practice) | [`ej10-vivero_base_v2`](../../tree/ej10-vivero_base_v2) | [`ej10-vivero_practice_v2`](../../tree/ej10-vivero_practice_v2) |

## Estado actual de las ramas de práctica v2

Actualizado el 12-nov-2026. Las ramas `_base`, `_base_v2` y `_practice` de ej01 a ej10 siguen en su estado original; lo que se ha movido son estas:

| Rama | Último commit | Hasta dónde llega |
|---|---|---|
| `ej02-biblioteca_practice_v2` | `3e7fcf0` "solucionado" (11-nov) | **Ejercicio terminado**: controlador completo (registrar, eliminar, alertas), vista, `module-info.java` y Gson en el `pom.xml`. Ojo: el commit incluye un `interiores.json` suelto que no pertenece al ejercicio. |
| `ej07-playlist_practice_v2` | `f87158c` "Vista-SceneBuilder-controller-base" (10-nov) | **Avanzada hasta Scene Builder**: vista hecha y controlador solo con los `@FXML` y los manejadores vacíos. Faltan `initialize`, registrar y eliminar. |
| `ej08-agenda_practice_v2` | `f843521` "Vista-SceneBuilder-controller-base" (10-nov) | **Igual que ej07**: vista y controlador base. Faltan `initialize`, registrar y eliminar. |
| `ej09-peliculas_practice_v2` | `b48045b` "Controller-sin-registrar-eliminar" (10-nov) | **Controlador casi completo**: vista, `initialize`, tabla, alertas y validaciones listos. **Faltan `onRegistrar` y `onEliminar`** (a propósito, se escriben a mano). |
| `ej10-vivero_practice_v2` | `7b10525` "Controller-sin-guardar-eliminar" (10-nov) | **Controlador casi completo**: vista, `initialize`, tabla y validaciones. **Faltan `onGuardar` y `onEliminar`**. |
| `ej01`, `ej03` a `ej06` `_practice_v2` | (sin cambios) | Sin empezar: están igual que su `_base_v2`. |

Soluciones modificadas recientemente:

- `ej09-peliculas_solucion` (`ea4180a`, 10-nov): el controlador se reemplazó por la versión del estudiante (alertas por tipo, `onRegistrar` y `onEliminar` completos). La prueba de ventana pasó 30 de 30.
- Las demás soluciones siguen como estaban el 8-nov.
