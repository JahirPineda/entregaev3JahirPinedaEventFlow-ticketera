    package cl.duoc.msvc_recintos.exception;

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String mensaje) {
            super(mensaje);
        }
    }