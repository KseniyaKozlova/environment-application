package by.itacademy.controllers.company;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;
import by.itacademy.services.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/version1")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(path = "/company")
    public CompanyResponseDto saveCompany(@RequestBody CreateCompanyRequestDto companyRequestDto) {
        return companyService.saveCompany(companyRequestDto);
    }

    @GetMapping(path = "/company/{id}")
    public CompanyResponseDto getCompanyById(@PathVariable(name = "id") UUID id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping(path = "/companies")
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.readCompanies();
    }

    @PutMapping(path = "/company/update/{id}")
    public CompanyResponseDto updateCompany(@PathVariable(name = "id") UUID id, @RequestBody UpdateCompanyRequestDto companyRequestDto) {
        return companyService.updateCompany(id, companyRequestDto);
    }

    @DeleteMapping(path = "/company/delete/{id}")
    public void deleteCompany(@PathVariable(name = "id") UUID id) {
        companyService.deleteCompany(id);
    }
}
