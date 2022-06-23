package br.com.bledson.ecommerce.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public record CreateProductCommand(@NotEmpty String name, @NotNull Long quantity, @NotEmpty Collection<String> images) {
}
