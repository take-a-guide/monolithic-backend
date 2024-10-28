package br.com.takeaguide.takeaguide.services;

import br.com.takeaguide.takeaguide.dtos.account.UserDto;
import br.com.takeaguide.takeaguide.dtos.ad.AdDto;
import br.com.takeaguide.takeaguide.dtos.ad.ChangeAdRequest;
import br.com.takeaguide.takeaguide.dtos.ad.CreateAdRequest;
import br.com.takeaguide.takeaguide.entities.Account;
import br.com.takeaguide.takeaguide.entities.Ad;
import br.com.takeaguide.takeaguide.repositories.mysql.AdRepository;
import br.com.takeaguide.takeaguide.repositories.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;

    public BigInteger createAd(String userId, byte[] ad) {
        return adRepository.createAd(userId, ad);
    }

    public BigInteger changeAd(Long id, byte[] ad) {
        return adRepository.updateAdContent(id, ad);
    }

    public void removeAd(Long id) {
        adRepository.softDeleteAd(id);
    }

    public List<AdDto> retrieveAdsById(Long idAd) {
        return adRepository.retrieveAdsById(idAd);
    }

    public List<AdDto> retrieveAdsByUserId(String userId) {
        return adRepository.retrieveAdsByUserId(userId);
    }
}