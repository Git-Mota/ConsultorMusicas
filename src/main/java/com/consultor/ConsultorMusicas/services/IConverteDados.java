package com.consultor.ConsultorMusicas.services;

public interface IConverteDados {
<T> T obterDados(String json, Class<T> classe);
}
