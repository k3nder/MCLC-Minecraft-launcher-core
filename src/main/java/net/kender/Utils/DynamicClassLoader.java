package net.kender.Utils;

public class DynamicClassLoader extends ClassLoader {
    public Class<?> loadClassFromSource(String nombreClase, String codigoFuente) throws Exception {
        byte[] bytecode = compile(nombreClase, codigoFuente);
        return defineClass(nombreClase, bytecode, 0, bytecode.length);
    }

    private byte[] compile(String nombreClase, String codigoFuente) throws Exception {
        // Aquí iría la lógica de compilación, por ejemplo, usando JavaCompiler API
        // En este ejemplo, simplemente convertimos el código fuente a bytes directamente
        return codigoFuente.getBytes();
    }
}
