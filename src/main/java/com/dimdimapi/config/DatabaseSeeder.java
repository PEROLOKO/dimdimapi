package com.dimdimapi.config;

import com.dimdimapi.model.Cliente;
import com.dimdimapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        clienteRepository.deleteAll();
        clienteRepository.saveAll(List.of(
                Cliente.builder().nome("Antonio Pietro Mateus Lopes").cpf("490.098.832-48").saldo(100.0).build(),
                Cliente.builder().nome("Stefany Marina Lopes").cpf("242.139.909-23").saldo(150.0).build(),
                Cliente.builder().nome("Roberto Osvaldo Barros").cpf("328.916.505-14").saldo(200.0).build(),
                Cliente.builder().nome("Enrico Geraldo Manoel da Mota").cpf("317.488.066-10").saldo(60.50).build()
        ));
    }

}
