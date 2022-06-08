import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { OpenmrsHttpClientService } from "src/app/shared/modules/openmrs-http-client/services/openmrs-http-client.service";
import { SystemSettingsWithKeyDetails } from "../models/system-settings.model";
import { capitalize } from "lodash";

@Injectable({
  providedIn: "root",
})
export class SystemSettingsService {
  constructor(private httpClient: OpenmrsHttpClientService) {}

  getFormPrivilegesConfigs(): Observable<any> {
    return this.httpClient
      .get(`systemsetting?q=iCare.privilegesAndFormsMappings&v=full`)
      .pipe(
        map((response) => {
          return JSON.parse(response?.results[0]?.value);
        }),
        catchError((error) => of(error))
      );
  }

  getSystemSettingsByKey(key: string): Observable<any> {
    return this.httpClient.get(`systemsetting?q=${key}&v=full`).pipe(
      map((response) => {
        return response?.results && response?.results[0]
          ? response?.results[0]?.value.indexOf("{") > -1 ||
            response?.results[0]?.value.indexOf("[") > -1
            ? JSON.parse(response?.results[0]?.value)
            : response?.results[0]?.value
          : "";
      }),
      catchError((error) => of(error))
    );
  }

  getSystemSettingsMatchingAKey(
    key: string
  ): Observable<SystemSettingsWithKeyDetails[]> {
    // lis.attributes.referringDoctor
    return this.httpClient.get(`systemsetting?q=${key}&v=full`).pipe(
      map((response) => {
        return response?.results && response?.results?.length > 0
          ? response?.results.map((result) => {
              return result?.value.indexOf("{") > -1 ||
                result?.value.indexOf("[") > -1
                ? {
                    name: result?.property
                      .split(".")
                      [result?.property.split(".").length - 1]?.toUpperCase(),
                    property: result?.property,
                    value: result?.value,
                  }
                : {
                    name: capitalize(
                      result?.property.split(".")[
                        result?.property.split(".").length - 1
                      ]
                    ),
                    property: result?.property,
                    value: result?.value,
                  };
            })
          : [];
      }),
      catchError((error) => of(error))
    );
  }

  getSystemSettingsDetailsByKey(key: string): Observable<any> {
    return this.httpClient.get(`systemsetting?q=${key}&v=full`).pipe(
      map((response) => {
        return {
          uuid: response?.results[0]?.uuid,
          key,
          value:
            response?.results && response?.results[0]
              ? response?.results[0]?.value.indexOf("{") > -1 ||
                response?.results[0]?.value.indexOf("[") > -1
                ? JSON.parse(response?.results[0]?.value)
                : response?.results[0]?.value
              : "",
        };
      }),
      catchError((error) => of(error))
    );
  }

  updateSystemSettings(data): Observable<any> {
    return this.httpClient.post(`systemsetting/${data?.uuid}`, data).pipe(
      map((response) => {
        return response;
      }),
      catchError((error) => of(error))
    );
  }

  getiCareServicesConfigurations(): Observable<any[]> {
    return this.httpClient
      .get(`systemsetting?q=iCare.Serives.Configurations&v=full`)
      .pipe(
        map((response) => {
          return JSON.parse(response?.results[0]?.value);
        })
      );
  }
}
