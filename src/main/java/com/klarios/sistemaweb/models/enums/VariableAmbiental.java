package com.klarios.sistemaweb.models.enums;

public enum VariableAmbiental {
    ILUMINACION {
        @Override
        public String toString() {
            return "iluminación";
        }
    },
    RUIDO{
        @Override
        public String toString() {
            return "ruido";
        }
    },
    TEMPERATURA{
        @Override
        public String toString() {
            return "temperatura";
        }
    },
    HUMEDAD{
        @Override
        public String toString() {
            return "humedad";
        }
    }
}
