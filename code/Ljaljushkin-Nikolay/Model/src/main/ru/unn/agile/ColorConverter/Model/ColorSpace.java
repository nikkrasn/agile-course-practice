package ru.unn.agile.ColorConverter.Model;

public abstract class ColorSpace {

    public abstract void initialize(Rgb color);

    public abstract Rgb toRgb();

    public <T extends ColorSpace> ColorSpace toColor(final Class<T> clazz) {

        T newColorSpace = null;
        try {

            if (clazz.getClass().equals(this.getClass())) {
                return ((ColorSpace) this.clone());
            }
            newColorSpace = clazz.newInstance();
            newColorSpace.initialize(toRgb());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newColorSpace;
    }
}
