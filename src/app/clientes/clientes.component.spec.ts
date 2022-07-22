import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';
import { ClientesComponent } from './clientes.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

describe('ClienteComponent', () => {
  let component: ClientesComponent;
  let fixture: ComponentFixture<ClientesComponent>;
  let clienteService: ClienteService;

  //beforeEach de configuración
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule], //testing  con peticiones http de prueba
      declarations: [ClientesComponent],
      providers: [ClienteService], //todos los parametros del constructor del componente
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA], //AÑADIR PARA EVITAR ERRORES
    }).compileComponents();
  });

  //beforeEach de instancias
  beforeEach(() => {
    fixture = TestBed.createComponent(ClientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); //entrara por el ngoninit
    clienteService = fixture.debugElement.injector.get(ClienteService);
    spyOn(clienteService, 'getClientes').and.callFake(() => null);
  });

  it('should create', () => {
    expect(component).toBeTruthy(); //comprobar que se instancie el componente
  });
});
