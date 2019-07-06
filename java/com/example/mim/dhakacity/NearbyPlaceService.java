package com.example.mim.dhakacity;

import com.example.mim.dhakacity.nearby.NearbyPlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NearbyPlaceService {

   @GET
    Call<NearbyPlaceResponse> getNearbyPlaceResponse(@Url String endUrl);
}
