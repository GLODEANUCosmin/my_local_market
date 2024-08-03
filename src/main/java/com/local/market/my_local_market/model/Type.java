package com.local.market.my_local_market.model;

import jakarta.annotation.Nullable;

public enum Type {
    Dairy,
    Meat,
    Honey,
    Fish,
    NonEdible,
    Berries,
    Cores,
    Citrus,
    Pits,
    Melons,
    Tropical,
    Root,
    Allium,
    PlantStem,
    Cruciferous,
    LeafyGreen,
    Marrow,
    Unknown;
    
    static public String typeToString(@Nullable Type tip){
        if (tip==null)
            return "Unknown";
        else{
            switch (tip){
                case Dairy:
                    return "Diary";
                case Meat:
                    return "Meat";
                case Honey:
                    return "Honey";
                case Fish:
                    return "Fish";
                case NonEdible:
                    return "Nonedible";
                case Berries:
                    return "Berries";
                case Cores:
                    return "Cores";
                case Citrus:
                    return "Citrus";
                case Pits:
                    return "Pits";
                case Tropical:
                    return "Tropical";
                case Melons:
                    return "Melons";
                case Root:
                    return "Root";
                case Allium:
                    return "Allium";
                case PlantStem:
                    return "Plantstem";
                case Cruciferous:
                    return "Cruciferous";
                case Marrow:
                    return "Marrow";
                default:
                    return "Unknown";
            }
        }

    }

    static public Type stringToType(@Nullable String string){
        if(string==null)
            return Type.Unknown;
        else
        {
        switch (string){
            case "Diary":
                return Type.Dairy;
                
            case "Meat":
                return Type.Meat;
                
            case "Honey":
                return Type.Honey;
                
            case "Fish":
                return Type.Fish;
                
            case "Nonedible":
                return Type.NonEdible;
                
            case "Cores":
                return Type.Cores;
                
            case "Berries":
                return Type.Berries;
                
            case "Citrus":
                return Type.Citrus;
                
            case "Pits":
                return Type.Pits;
                
            case "Melons":
                return Type.Melons;
                
            case "Tropical":
                return Type.Tropical;
                
            case "Root":
                return Type.Root;
                
            case "Allium":
                return Type.Allium;
                
            case "Plantstem":
                return Type.PlantStem;
                
            case "Cruciferous":
                return Type.Cruciferous;
                
            case "Leafygreen":
                return Type.LeafyGreen;
                
            case "Marrow":
                return Type.Marrow;
                
            default:
                return Type.Unknown;

        }
        }
    }

}
