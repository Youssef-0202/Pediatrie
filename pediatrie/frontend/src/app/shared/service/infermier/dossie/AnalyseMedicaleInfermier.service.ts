import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';

import {environment} from 'src/environments/environment';
import {PaginatedList} from 'src/app/zynerator/dto/PaginatedList.model';
import * as moment from 'moment/moment';

import {AnalyseMedicaleDto} from 'src/app/shared/model/dossie/AnalyseMedicale.model';
import {AnalyseMedicaleCriteria} from 'src/app/shared/criteria/dossie/AnalyseMedicaleCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';


@Injectable({
  providedIn: 'root'
})
export class AnalyseMedicaleInfermierService {
    protected _API = '';
    protected _items: Array<AnalyseMedicaleDto>;
    protected _item: AnalyseMedicaleDto;
    protected _selections: Array<AnalyseMedicaleDto>;
    protected _createDialog: boolean;
    protected _editDialog: boolean;
    protected _viewDialog: boolean;
    protected _criteria: AnalyseMedicaleCriteria;
    protected _validate = false;

    private _createActionIsValid = true;
    private _editActionIsValid = true;
    private _listActionIsValid = true;
    private _deleteActionIsValid = true;
    private _viewActionIsValid = true;
    private _duplicateActionIsValid = true;


    private _createAction = 'create';
    private _listAction = 'list';
    private _editAction = 'edit';
    private _deleteAction = 'delete';
    private _viewAction = 'view';
    private _duplicateAction = 'duplicate';
    private _entityName: string;

    protected API_PERMISSION: string ;


    constructor(private http: HttpClient) {
        this.API_PERMISSION = environment.apiUrl + 'modelPermissionUser/';
    }


    public findAll() {
        return this.http.get<Array<AnalyseMedicaleDto>>(this.API);
    }

    public findAllOptimized() {
        return this.http.get<Array<AnalyseMedicaleDto>>(this.API + 'optimized');
    }

    public findPaginatedByCriteria(criteria: AnalyseMedicaleCriteria): Observable<PaginatedList<AnalyseMedicaleDto>> {
        return this.http.post<PaginatedList<AnalyseMedicaleDto>>(this.API + 'find-paginated-by-criteria', criteria);
    }

    public save(): Observable<AnalyseMedicaleDto> {
        return this.http.post<AnalyseMedicaleDto>(this.API, this.item);
    }

    findByConsultationRef(ref: string) {
        return this.http.get<Array<AnalyseMedicaleDto>>('http://localhost:8036/api/infermier/analyseMedicale/consultation/num/'+ref)
    }

    public delete(dto: AnalyseMedicaleDto) {
        return this.http.delete<number>(this.API + 'id/' + dto.id);
    }


    public edit(): Observable<AnalyseMedicaleDto> {
        return this.http.put<AnalyseMedicaleDto>(this.API, this.item);
    }


    public findByCriteria(criteria: AnalyseMedicaleCriteria): Observable<Array<AnalyseMedicaleDto>> {
        return this.http.post<Array<AnalyseMedicaleDto>>(this.API + 'find-by-criteria', criteria);
    }

    public findByIdWithAssociatedList(item: AnalyseMedicaleDto): Observable<AnalyseMedicaleDto> {
        return this.http.get<AnalyseMedicaleDto>(this.API + 'id/' + item.id);
    }

    public deleteMultiple() {
        return this.http.post<void>(this.API + 'multiple', this.selections);
    }
    public exportPdf(element: AnalyseMedicaleDto): Observable<ArrayBuffer> {
        return this.http.post(this.API + 'exportPdf/', element, {responseType: 'arraybuffer'});
    }

    public hasActionPermission(username: string, actionReference: string): Observable<boolean> {
        // tslint:disable-next-line:max-line-length
        return this.http.get<boolean>(this.API_PERMISSION + 'user/' + username + '/model/' + this.entityName + '/action/' + actionReference);
    }

    public importExcel(file: File): Observable<Array<AnalyseMedicaleDto>> {
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);
        return this.http.post<Array<AnalyseMedicaleDto>>(this.API + 'import-excel', formData);
    }



    public format(myDate: Date): Date {
        if (myDate != null) {
            const newdate = new Date(myDate);
            const formattedDate = moment(newdate).format(environment.dateFormatEdit);
            console.log(formattedDate);
            myDate = new Date(formattedDate);
        }
        return myDate;
    }

    get API() {
        return environment.apiUrlPediatrieservice + 'infermier/analyseMedicale/';
    }

    public get items(): Array<AnalyseMedicaleDto> {
        if (this._items == null) {
            this._items = new Array<AnalyseMedicaleDto>();
        }
        return this._items;
    }

    public set items(value: Array<AnalyseMedicaleDto>) {
        this._items = value;
    }

    public get item(): AnalyseMedicaleDto {
        if (this._item == null) {
            this._item = new AnalyseMedicaleDto();
        }
        return this._item;
    }

    public set item(value: AnalyseMedicaleDto) {
        this._item = value;
    }

    public get selections(): Array<AnalyseMedicaleDto> {
        if (this._selections == null) {
            this._selections = new Array<AnalyseMedicaleDto>();
        }
        return this._selections;
    }


    public set selections(value: Array<AnalyseMedicaleDto>) {
        this._selections = value;
    }

    public get createDialog(): boolean {
        return this._createDialog;
    }

    public set createDialog(value: boolean) {
        this._createDialog = value;
    }

    public get editDialog(): boolean {
        return this._editDialog;
    }

    public set editDialog(value: boolean) {
        this._editDialog = value;
    }

    public get viewDialog(): boolean {
        return this._viewDialog;
    }

    public set viewDialog(value: boolean) {
        this._viewDialog = value;
    }

    public get criteria(): AnalyseMedicaleCriteria {
        if (this._criteria == null) {
            this._criteria = new AnalyseMedicaleCriteria();
        }
        return this._criteria;
    }

    public set criteria(value: AnalyseMedicaleCriteria) {
        this._criteria = value;
    }


    public setApi(API: string) {
        this._API = API;
    }

    set validate(value: boolean) {
        this._validate = value;
    }


    get createAction(): string {
        return this._createAction;
    }

    set createAction(value: string) {
        this._createAction = value;
    }

    get listAction(): string {
        return this._listAction;
    }

    set listAction(value: string) {
        this._listAction = value;
    }

    get editAction(): string {
        return this._editAction;
    }

    set editAction(value: string) {
        this._editAction = value;
    }

    get deleteAction(): string {
        return this._deleteAction;
    }

    set deleteAction(value: string) {
        this._deleteAction = value;
    }

    get createActionIsValid(): boolean {
        return this._createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this._createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this._editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this._editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this._listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this._listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this._deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this._deleteActionIsValid = value;
    }

    get viewAction(): string {
        return this._viewAction;
    }

    set viewAction(value: string) {
        this._viewAction = value;
    }

    get duplicateAction(): string {
        return this._duplicateAction;
    }

    set duplicateAction(value: string) {
        this._duplicateAction = value;
    }

    get viewActionIsValid(): boolean {
        return this._viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this._viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this._duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this._duplicateActionIsValid = value;
    }

    get entityName(): string {
        return this._entityName;
    }

    set entityName(value: string) {
        this._entityName = value;
    }

}
