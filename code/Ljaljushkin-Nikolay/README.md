## Перевод из одного цветового пространства в другое (RGB, HSV, LAB)

Выполнил:

 - Лялюшкин Николай
 - ННГУ, ф-т ВМК, каф. МО ЭВМ, группа 85м21

## Задание

Реализовать классы, позволяющие конвертировать одно цветовое пространство в другое. 

Ссылки на математические формулы соответствующих преобразований цветовых пространств можно найти в таблице.

+------+--------------------------------------------------------------------+
| From |                                 TO                                 |
+      +--------------------------------------------------------------------+
|      |         RGB          |          LAB         |          HSV         |
+------+----------------------+----------------------+----------------------+
|  RGB |                      | RGB to XYZ [RGB2XYZ] | RGB to HSV [RGB2HSV] |
|      |                      | XYZ to LAB [XYZ2LAB] |                      |
+------+----------------------+----------------------+----------------------+
|  LAB | LAB to XYZ [LAB2XYZ] |                      | LAB to XYZ [LAB2XYZ] |
|      | XYZ to RGB [XYZ2RGB] |                      | XYZ to RGB [XYZ2RGB] |
|      |                      |                      | RGB to HSV [RGB2HSV] |
+------+----------------------+----------------------+----------------------+
|  HSV | HSV to RGB [HSV2RGB] | HSV to RGB [HSV2RGB] |                      |
|      |                      | RGB to XYZ [RGB2XYZ] |                      |
|      |                      | XYZ to LAB [XYZ2LAB] |                      |
+------+----------------------+----------------------+----------------------+



## Документация разработчика

TBD

<!-- LINKS -->

[LAB2XYZ]: http://www.brucelindbloom.com/index.html?Eqn_Lab_to_XYZ.html
[RGB2XYZ]: http://www.brucelindbloom.com/index.html?Eqn_RGB_to_XYZ.html
[XYZ2LAB]: http://www.brucelindbloom.com/index.html?Eqn_XYZ_to_Lab.html
[XYZ2RGB]: http://www.brucelindbloom.com/index.html?Eqn_XYZ_to_RGB.html
[HSV2RGB]: http://www.rapidtables.com/convert/color/hsv-to-rgb.htm
[RGB2HSV]: http://www.rapidtables.com/convert/color/rgb-to-hsv.htm