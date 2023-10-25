package com.example.demoApi.controller.resource;

import com.example.demoApi.entity.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Tag(name = "Product",description = "Operations related with product")
public interface ProductoResource {

    @Operation(description = "Get the information of all the product", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information of all the product",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation =List.class)))})
    public ResponseEntity<List<Producto>> getAllProductos();

    @Operation(description = "Get the information about one product", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information about one product",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation =Producto.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the product to search")
            })
    public ResponseEntity<Producto> getProductosId(@PathVariable Integer id);

    @Operation(description = "Create one product", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Return the created product",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation =Producto.class)))})
    public ResponseEntity<Producto> create(@RequestBody @Valid Producto producto);

    @Operation(description = "Update one product", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return update one product",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation =Producto.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the product to update")
            })
    public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto producto);

    @Operation(description = "Delete one product", responses = {
            @ApiResponse(responseCode = "204",
                    description = "Return nothing",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation =void.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the product to delete")
            })
    public ResponseEntity<Void> delete(@PathVariable Integer id);
}
