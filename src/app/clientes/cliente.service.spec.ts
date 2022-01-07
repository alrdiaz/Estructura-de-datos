import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { ClienteService } from './cliente.service';
import { RouterTestingModule } from '@angular/router/testing';
import { Cliente } from './cliente';
import { HttpHeaders } from '@angular/common/http';

const data: Cliente[] = [
  {
    nmid: 1047412114,
    cus_nmcliente: 1,
    cus_dsnombres: 'Alejandro Luis',
    cus_dsapellidos: 'Romero Diaz',
    cus_dsdireccion: 'tr9#5-84',
    cus_dscorreo: 'aromero4@eafit.edu.co',
    cus_cdtelefono: '6046604072',
    cus_cdtelefonoalter: '6046600317',
    cus_cdcelular: '3017957267',
    cus_nmcargo: 5,
    cus_dscargo: 'Ingeniero de control',
    cus_nmciudad: 55457,
    cus_dsciudad: 'Sabaneta',
    cus_fenacimiento: new Date('1989-08-07'),
    cus_genero: 'M',
    cus_nmcomunidad: 5,
    cus_dscomunidad: 'Villas del Carmen',
    cus_dsempresalabora: 'HMV Ingenieros',
    cus_nmdivision: 5,
    cus_dsdivision: 'EPC',
    cus_nmpais: 57,
    cus_dspais: 'Colombia',
    cus_hobbies: 'Pintura, Futbol, Musica, Cine, Runnig, Viajar',
    cus_febaja: new Date('2021-12-28T18:59:00.000-05:00'),
    cus_feregistro: new Date('2021-12-28T18:59:00.000-05:00'),
  },
  {
    nmid: 1128417975,
    cus_nmcliente: 2,
    cus_dsnombres: 'Greisy Dhaliana',
    cus_dsapellidos: 'Jaramillo Tobias',
    cus_dsdireccion: 'tr9#5-84',
    cus_dscorreo: 'dhalijt@gmail.com',
    cus_cdtelefono: '6046604072',
    cus_cdtelefonoalter: '6046600317',
    cus_cdcelular: '3014863078',
    cus_nmcargo: 5,
    cus_dscargo: 'Psicologa',
    cus_nmciudad: 55457,
    cus_dsciudad: 'Sabaneta',
    cus_fenacimiento: new Date('1988-06-24'),
    cus_genero: 'F',
    cus_nmcomunidad: 5,
    cus_dscomunidad: 'Villas del Carmen',
    cus_dsempresalabora: 'Casablanca Médica',
    cus_nmdivision: 5,
    cus_dsdivision: 'sst',
    cus_nmpais: 57,
    cus_dspais: 'Colombia',
    cus_hobbies: 'Musica, Cine, Teatro, Poesia, Leer, Viajar',
    cus_febaja: new Date('2021-12-28T18:59:00.000-05:00'),
    cus_feregistro: new Date('2021-12-28T18:59:00.000-05:00'),
  },
];

const id: number = 1047412114;

const deleteResp: any = {
  cliente: {
    nmid: 1047412114,
    cus_nmcliente: 1,
    cus_dsnombres: 'Alejandro Luis',
    cus_dsapellidos: 'Romero Diaz',
    cus_dsdireccion: 'tr9#5-84',
    cus_dscorreo: 'aromero4@eafit.edu.co',
    cus_cdtelefono: '6046604072',
    cus_cdtelefonoalter: '6046600317',
    cus_cdcelular: '3017957267',
    cus_nmcargo: 5,
    cus_dscargo: 'Ingeniero de control',
    cus_nmciudad: 55457,
    cus_dsciudad: 'Sabaneta',
    cus_fenacimiento: '1989-08-07',
    cus_genero: 'M',
    cus_nmcomunidad: 5,
    cus_dscomunidad: 'Villas del Carmen',
    cus_dsempresalabora: 'HMV Ingenieros',
    cus_nmdivision: 5,
    cus_dsdivision: 'EPC',
    cus_nmpais: 57,
    cus_dspais: 'Colombia',
    cus_hobbies: 'Pintura, Futbol, Musica, Cine, Runnig, Viajar',
    cus_febaja: '2021-12-28T18:59:00.000-05:00',
    cus_feregistro: '2021-12-28T18:59:00.000-05:00',
  },
  mensaje: 'El cliente ha sido eliminado con éxito!',
};

const postResp: any = {
  cliente: {
    nmid: 1047412114,
    cus_nmcliente: 1,
    cus_dsnombres: 'Alejandro Luis',
    cus_dsapellidos: 'Romero Diaz',
    cus_dsdireccion: 'tr9#5-84',
    cus_dscorreo: 'aromero4@eafit.edu.co',
    cus_cdtelefono: '6046604072',
    cus_cdtelefonoalter: '6046600317',
    cus_cdcelular: '3017957267',
    cus_nmcargo: 5,
    cus_dscargo: 'Ingeniero de control',
    cus_nmciudad: 55457,
    cus_dsciudad: 'Sabaneta',
    cus_fenacimiento: '1989-08-07T00:00:00.000-05:00',
    cus_genero: 'M',
    cus_nmcomunidad: 5,
    cus_dscomunidad: 'Villas del Carmen',
    cus_dsempresalabora: 'HMV Ingenieros',
    cus_nmdivision: 5,
    cus_dsdivision: 'EPC',
    cus_nmpais: 57,
    cus_dspais: 'Colombia',
    cus_hobbies: 'Pintura, Futbol, Musica, Cine, Runnig, Viajar',
    cus_febaja: '2021-12-28T18:59:00.000-05:00',
    cus_feregistro: '2021-12-28T18:59:00.000-05:00',
  },
  mensaje: 'El cliente ha sido creado con éxito!',
};

const putResp: any = {
  cliente: {
    nmid: 1047412114,
    cus_nmcliente: 1,
    cus_dsnombres: 'Alejandro Luis',
    cus_dsapellidos: 'Romero Diaz',
    cus_dsdireccion: 'tr9#5-84',
    cus_dscorreo: 'aromero4@eafit.edu.co',
    cus_cdtelefono: '6046604072',
    cus_cdtelefonoalter: '6046600317',
    cus_cdcelular: '3008168775',
    cus_nmcargo: 5,
    cus_dscargo: 'Ingeniero de control',
    cus_nmciudad: 55457,
    cus_dsciudad: 'Sabaneta',
    cus_fenacimiento: '1989-08-07T00:00:00.000-05:00',
    cus_genero: 'M',
    cus_nmcomunidad: 5,
    cus_dscomunidad: 'Villas del Carmen',
    cus_dsempresalabora: 'HMV Ingenieros',
    cus_nmdivision: 5,
    cus_dsdivision: 'EPC',
    cus_nmpais: 57,
    cus_dspais: 'Colombia',
    cus_hobbies: 'Pintura, Futbol, Musica, Cine, Runnig, Viajar',
    cus_febaja: '2021-12-28T18:59:00.000-05:00',
    cus_feregistro: '2021-12-28T18:59:00.000-05:00',
  },
  mensaje: 'El cliente ha sido actualizado con éxito!',
};

describe('ClienteService', () => {
  let clienteService: ClienteService;
  let httpMock: HttpTestingController;
  const urlEndPointGetAll: string = 'http://localhost:8080/clientes/tree';
  const urlEndPointFind: string = 'http://localhost:8080/clientes/find';
  const urlEndPointSave: string = 'http://localhost:8080/clientes/save';
  const urlEndPointUpdate: string = 'http://localhost:8080/clientes/update';
  const urlEndPointDelete: string = 'http://localhost:8080/clientes/delete';
  const httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [ClienteService],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    });
  });

  beforeEach(() => {
    clienteService = TestBed.inject(ClienteService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  afterEach(() => {
    httpMock.verify();
  });

  it('should create', () => {
    expect(clienteService).toBeTruthy();
  });

  it('getClientes return a list of Clientes and does a GET method', () => {
    clienteService
      .getClientes()
      .subscribe((resp: Cliente[]) => expect(resp).toEqual(data));
    const req = httpMock.expectOne(urlEndPointGetAll);
    expect(req.request.method).toBe('GET');
    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');
    req.flush(data);
    httpMock.verify();
  });

  it('getCliente return an Cliente by id and does a GET method', () => {
    clienteService.getCliente(1047412114).subscribe((res) => {
      expect(res).toEqual(data[0]);
    });
    const req = httpMock.expectOne(urlEndPointFind + '/1047412114');
    expect(req.request.method).toBe('GET');
    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');
    req.flush(data[0]);
    httpMock.verify();
  });

  it('delete return delete response for url/id does a DELETE method by id ', () => {
    clienteService.delete(1047412114).subscribe((res) => {
      expect(res).toBe(deleteResp);
    });
    const req = httpMock.expectOne(urlEndPointDelete + '/1047412114');
    expect(req.request.method).toBe('DELETE');
    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');
    req.flush(deleteResp);
    httpMock.verify();
  });

  it('create return create response  does a POST method by cliente sended ', () => {
    clienteService.create(data[0]).subscribe((res) => {
      expect(res).toBe(postResp);
    });
    const req = httpMock.expectOne(urlEndPointSave);
    expect(req.request.method).toBe('POST');
    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');
    req.flush(postResp);
    httpMock.verify();
  });

  it('update return update response does a PUT method by cliente sended ', () => {
    clienteService.update(data[0]).subscribe((res) => {
      expect(res).toBe(putResp);
    });
    const req = httpMock.expectOne(urlEndPointUpdate + '/' + id);
    expect(req.request.method).toBe('PUT');
    expect(req.cancelled).toBeFalsy();
    expect(req.request.responseType).toEqual('json');
    req.flush(putResp);
    httpMock.verify();
  });
});
