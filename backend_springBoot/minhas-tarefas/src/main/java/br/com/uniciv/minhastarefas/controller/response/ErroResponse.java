package br.com.uniciv.minhastarefas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErroResponse {

    private String campo;
    private String message;

    public ErroResponse(String message) {

        this.message = message;
    }
}
