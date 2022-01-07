import { ClienteService } from './cliente.service';
import { Cliente } from './cliente';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { FormComponent } from './form.component';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;

  //beforeEach de configuración
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule], //testing  con peticiones http de prueba
      declarations: [FormComponent],
      providers: [ClienteService], //todos los parametros del constructor del componente
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA], //AÑADIR PARA EVITAR ERRORES
    });
  });

  //beforeEach de instancias
  beforeEach(() => {
    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); //entrara por el ngoninit
  });

  it('should create', () => {
    expect(component).toBeTruthy(); //comprobar que se instancie el componente
  });
});
