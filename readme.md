
# EmlakBurada - Hepsiemlak Java Spring Bitirme Projesi

Bu proje bir emlak sitesi backend projesidir. 

- Kullanıcılar kayıt olabilir.
- Email ve şifresi ile giriş yapabilir. Giriş yapınca token oluşturulur. Bu token ile yetkili olduğu işlemleri gerçekleştirebilir.
- Her kullanıcı kayıt olduktan sonra ilan yayınlama hakkı "0"dır. İlan yayınlaması için paket satın alması gerekir.
- İlan yayınlama hakkı ve paketin geçerlilik süresi kullanıcı entity'sine tanımlanmıştır.
- Paket içeriğinde 10 adet ilan yayınlama hakkı mevcuttur. Kullanım süresi 30 gündür.
- Kullanıcı paket satın almak istediğinde kendi ilan bakiyesi ve kullanım süresi kontrol edilir. Kullanım süresi geçmişse kalan haklar sıfırlanır. Kullanıcı ödemeyi gerçekleştirirse paket rabbitMQ üzerinden kullanıcının profiline tanımlanır. 10 adet ilan yayınlama hakkı ve satın aldığı gün baz alınarak 30 gün kullanım süresi eklenir.
- Kullanım süresi geçmemişse bakiye sıfırlanmaz. Ödeme işlemi doğruysa, kalan kullanım haklarının üzerine 10 adet ilan hakkı eklenir, kullanım süresi 30 gün artırılır.
- Satın alma işlemi giriş yapan kullanıcının id'sine göre yapılır. (Bu bilgi header daki tokendan alınır.)
- Kullanıcı ilan oluşturmak istediğinde, ilan hakları ve kullanım süresi kontrol edilir. Paketin süresi geçmişse ya da ilan hakkı yoksa, kalan haklarını sıfırlar. İlan yayınlama işlemi iptal olur. Kullanıcıya hata mesajı döner. Kullanım süresi geçmemişse ve ilan hakkı varsa ilan oluşturulur. Kullanıcının bakiyesinden düşülür.
- İlan oluşturulduğunda ilan statüsü IN_REVIEW 'dir. Kullanıcı ilanın statüsünü başka bir servisten ACTIVE ya da PASSIVE duruma getirebilir.
- İlanların süresi 30 gündür.


### Aşağıdaki işlemler sadece giriş yapan yetkili kullanıcı tarafından yapılabilir:

- Kullanıcı kendi bakiye ve kullanım süresi kontrolünü yapabilir. Profil detaylarına bakabilir.
- Kullanıcı ilan oluşturabilir, düzenleyebilir, silebilir, detaylarına ulaşabilir.
- Kendi ilanlarını aktif ve pasif duruma getirebilir.
- Satın alma işlemi yapabilir, satın aldığı paketlere bakabilir.




### Gerekli açıklamalar proje içindeki methodların üzerinde yapılmıştır.

## UML Diagram

![Untitled Diagram drawio (2)](https://user-images.githubusercontent.com/81576354/159819911-be0d7d65-8a1b-4d0c-98b9-de0fa47fc787.png)

6 microservice bulunmaktadır. Hepsi birbiri ile iletişim halindedir. 6 microservice gateway'den yönetilmektedir. Service discovery tarafından instance yönetimi sağlanmaktadır.
- Advert statüsü değiştirme işlemi AdvertService tarafından queue'ya eklenip, AdvertStatusService'e asenkron olarak yaptırılmaktadır.
- Paket tanımlama işlemi PurchaseService tarafından queue'ya eklenip UserService'e asenkron yaptırılmaktadır.

## API Kullanımı

#### Postman Api Dökümantasyonu:

https://documenter.getpostman.com/view/18399023/UVsSP4PE

### individual-user controller

![individual-user](https://user-images.githubusercontent.com/81576354/159820024-267c400f-4861-4d9d-8e0e-a480fa4a3f63.png)

### advert controller

![advert-controller](https://user-images.githubusercontent.com/81576354/159820063-951e5bcc-af66-43e6-af3b-525c09c2d364.png)

### purchase controller

![purchase-controller](https://user-images.githubusercontent.com/81576354/159820077-3ade26d6-fb17-4ef6-852d-072f721f47be.png)

### payment controller

![payment-controller](https://user-images.githubusercontent.com/81576354/159820099-b15a8297-5d3c-4961-9242-49a0dbd2233e.png)

### auth controller

![auth-controller](https://user-images.githubusercontent.com/81576354/159820104-a84223b2-8a3e-4bce-9824-89f724ccf9ff.png)

## Queue
### Proje ayağa kaldırılmadan önce resimdeki queue'lar rabbitMQ'ya eklenmelidir. Eklenmediği durumda listener hatası alınacaktır.  
  
  ![queue](https://user-images.githubusercontent.com/81576354/159820116-956fa8e7-8061-45a2-b7fd-72f31b479a07.png)

## Coverage
#### Advert

![advert-coverage](https://user-images.githubusercontent.com/81576354/159820279-24ed7f6f-427c-4767-80df-6e5a809947d0.png)

#### User

![user-coverage](https://user-images.githubusercontent.com/81576354/159820297-8959740d-a50c-4c21-8831-8b7c061a9c2e.png)

#### Purchase

![purchase-coverage](https://user-images.githubusercontent.com/81576354/159820312-8d88d05d-770a-40d6-b329-10f4c8ff77b1.png)

## Kullanılan Teknolojiler

- Service Discovery
- Feign Client
- RabbitMQ
- PostgreSQL
- Modelmapper
- Lombok
- Spring Boot Web
- Hibernate
- Spring Data Jpa
- Swagger (OpenApi)

  
## Teşekkür

- Bize eğitim boyunca önemli bilgiler veren, sektörden örnekler sunan eğitmenimiz Cem DİRMAN'a teşekkürler.
- Bootcamp için Hepsiemlak ve Patika.dev' e teşekkürler.

  
