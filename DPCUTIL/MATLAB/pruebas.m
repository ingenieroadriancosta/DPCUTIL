% function pruebas
clc;clear all;
javaaddpath('POINTERSLIBRARY.jar');


a = Pointers.DPCUTIL;
d = int32([1 2]);
a.DvmgStartConfigureDevicesN( 0, d );


