package com.github.rcd27.dribbbleapp.model;


import com.github.rcd27.dribbbleapp.model.objects.ShotDataTransferObject;

import java.util.List;

import io.reactivex.Single;

public interface Model {

    Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount);

}
