## Перевод из одного цветового пространства в другое (RGB, HSV, LAB)

Выполнил:

 - Лялюшкин Николай
 - ННГУ, ф-т ВМК, каф. МО ЭВМ, группа 85м21

## Задание

Реализовать классы, позволяющие конвертировать одно цветовое пространство в другое. 

Ссылки на математические формулы соответствующих преобразований цветовых пространств можно найти в таблице.
<table>
  <tr>
    <th></th>
    <th>RGB</th>
    <th>LAB</th>
    <th>HSV</th>
  </tr>
  <tr>
    <td>RGB</td>
    <td></td>
    <td>[RGBtoXYZ][RGB2XYZ]<br>[XYZtoLAB][XYZ2LAB]</td>
    <td>[RGBtoHSV][RGB2HSV]</td>
  </tr>
  <tr>
    <td>LAB</td>
    <td>[LABtoXYZ][LAB2XYZ] <br>[XYZtoRGB] [XYZ2RGB]</td>
    <td></td>
    <td>[LABtoXYZ][LAB2XYZ] <br>[XYZtoRGB] [XYZ2RGB] <br>[RGBtoHSV][RGB2HSV]</td>
  </tr>
  <tr>
    <td>HSV</td>
    <td>[HSVtoRGB][HSV2RGB]</td>
    <td>[HSVtoRGB][HSV2RGB]<br>[RGBtoXYZ][RGB2XYZ]<br>[XYZtoLAB][XYZ2LAB]</td>
    <td></td>
  </tr>
</table>

## Документация разработчика

TBD

<!-- LINKS -->

[LAB2XYZ]: http://www.brucelindbloom.com/index.html?Eqn_Lab_to_XYZ.html
[RGB2XYZ]: http://www.brucelindbloom.com/index.html?Eqn_RGB_to_XYZ.html
[XYZ2LAB]: http://www.brucelindbloom.com/index.html?Eqn_XYZ_to_Lab.html
[XYZ2RGB]: http://www.brucelindbloom.com/index.html?Eqn_XYZ_to_RGB.html
[HSV2RGB]: http://www.rapidtables.com/convert/color/hsv-to-rgb.htm
[RGB2HSV]: http://www.rapidtables.com/convert/color/rgb-to-hsv.htm